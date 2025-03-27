package com.jbe01.r2sshop.util;

import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class JwtUtil {

    @Autowired
    UserRepository userRepository;

    //    @Value( "${jwt.config.security-key}" )
    private static String SECRET_KEY = "your-256-bit-secret-key-here-very-long-and-secure";

    //    @Value("${jwt.config.expiration-time}}")
    private static long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    public String generateToken(UserDetails userDetails, Map<String, Object> extractClaim) {
        return Jwts.builder().claims(extractClaim)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean checkRoles(String... roles) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users user = userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));

        Stream<String> userRoles = user.getUserRoles().stream().map((userRole -> userRole.getRoles().getName()));

        return userRoles.toList().containsAll(Arrays.asList(roles));
    }
}

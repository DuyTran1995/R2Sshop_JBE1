package com.jbe01.r2sshop.util;

import com.jbe01.r2sshop.entity.Roles;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.UserRepository;
import com.jbe01.r2sshop.service.RoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtUtil {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Value("${jwt.config.security-key}")
    private String SECRET_KEY;

    @Value("${jwt.config.expiration-time}")
    private String EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails, Map<String, Object> extractClaim) {
        return Jwts.builder().claims(extractClaim)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Integer.parseInt(EXPIRATION_TIME) * 1000L))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserIdFromToken(String token) {
        try {
            Claims claims = this.extractAllClaims(token);
            Object userId = claims.get("user_id");
            if (userId == null) {
                throw new NotFoundException("User ID not found in token");
            }

            Long userIdValue = null;

            if (userId instanceof Integer) {
                userIdValue = ((Integer) userId).longValue();
            } else if (userId instanceof Long) {
                userIdValue = (Long) userId;
            } else {
                throw new RuntimeException("User ID is not a valid number: " + userId);
            }

            return userIdValue;
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token: " + e.getMessage());
        }
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

        Users user = userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found" + userDetails.getUsername()));

        Stream<String> userRoles = user.getUserRoles().stream().map((userRole -> userRole.getRoles().getName()));

        Set<String> requiredRoles = new HashSet<>(Arrays.asList(roles));

        return userRoles.anyMatch(requiredRoles::contains);
    }
}

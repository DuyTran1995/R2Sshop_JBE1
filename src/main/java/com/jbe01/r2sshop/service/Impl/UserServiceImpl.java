package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.dto.requests.SignInRequestDto;
import com.jbe01.r2sshop.dto.responses.SignInResponseDto;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.UserRepository;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public Users signUp(Users users) {

        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return userRepository.save(users);
    }

    public SignInResponseDto signIn(SignInRequestDto requestBody) {
        var foundUser = userRepository.findUserByEmail(requestBody.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(foundUser.getEmail());

        Map<String, Object> extractClaim = new HashMap<>();

        extractClaim.put("firstName", foundUser.getFirstName());
        extractClaim.put("lastName", foundUser.getLastName());
        extractClaim.put("phone", foundUser.getPhone());
        extractClaim.put("email", foundUser.getEmail());
        extractClaim.put("roles", foundUser.getUserRoles().stream()
                .map(userRole -> userRole.getRoles().getName())
                .collect(Collectors.toList()));

        String jwt = jwtUtil.generateToken(userDetails, extractClaim);

        return SignInResponseDto.builder().accessToken(jwt).build();
    }
}

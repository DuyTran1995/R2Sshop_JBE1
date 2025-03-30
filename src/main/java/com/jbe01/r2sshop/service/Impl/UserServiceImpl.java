package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.dto.requests.SignInRequestDto;
import com.jbe01.r2sshop.dto.responses.SignInResponseDto;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.UserRepository;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        extractClaim.put("user_id", foundUser.getId());
        extractClaim.put("roles", foundUser.getUserRoles().stream()
                .map(userRole -> userRole.getRoles().getName())
                .collect(Collectors.toList()));

        String jwt = jwtUtil.generateToken(userDetails, extractClaim);

        return SignInResponseDto.builder().accessToken(jwt).build();
    }

    public Users findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User not found" + email));
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found" + id));
    }

    public List<Users> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).stream().toList();
    }

    public void delete(Long id) {
        var foundUser = this.findById(id);

        userRepository.delete(foundUser);
    }

    public void update(Users users, Long id) {
        var foundUser = this.findById(id);

        foundUser.setFirstName(users.getFirstName());
        foundUser.setLastName(users.getLastName());
        foundUser.setPhone(users.getPhone());
        foundUser.setEmail(users.getEmail());
        userRepository.save(foundUser);
    }

    public List<Users> findUsersByIsEnabled(boolean isEnabled) {
        return userRepository.findUsersByEnabled(isEnabled);
    }

    public int countUsersByIsEnabled(boolean isEnabled) {
        return userRepository.countUsersByEnabled(isEnabled);
    }

    public int countUsers() {
        return (int) userRepository.count();
    }
}

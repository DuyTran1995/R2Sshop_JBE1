package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.dto.requests.SignInRequestDto;
import com.jbe01.r2sshop.dto.responses.SignInResponseDto;
import com.jbe01.r2sshop.entity.Users;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {
    Users signUp(Users users);
    SignInResponseDto signIn(SignInRequestDto request);
    Users findByEmail(String email);
    Users findById(Long id);
    List<Users> findAll(PageRequest pageRequest);
    void delete(Long id);
    void update(Users users, Long id);
    List<Users> findUsersByIsEnabled(boolean isEnabled);
    int countUsersByIsEnabled(boolean isEnabled);
    int countUsers();
}

package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.dto.requests.SignInRequestDto;
import com.jbe01.r2sshop.dto.responses.SignInResponseDto;
import com.jbe01.r2sshop.entity.Users;

public interface UserService {
    Users signUp(Users users);
    SignInResponseDto signIn(SignInRequestDto request);
    Users findByEmail(String email);
}

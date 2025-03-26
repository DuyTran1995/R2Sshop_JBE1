package com.jbe01.r2sshop.dto.requests;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String email;
    private String password;
}

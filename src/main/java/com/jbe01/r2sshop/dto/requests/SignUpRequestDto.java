package com.jbe01.r2sshop.dto.requests;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String[] roles;
}

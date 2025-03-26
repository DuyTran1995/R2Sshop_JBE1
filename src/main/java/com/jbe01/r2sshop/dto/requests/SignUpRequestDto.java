package com.jbe01.r2sshop.dto.requests;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}

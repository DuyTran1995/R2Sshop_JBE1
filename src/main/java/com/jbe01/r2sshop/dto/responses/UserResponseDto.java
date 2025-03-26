package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled;
    private List<String> roleNames;
}

package com.jbe01.r2sshop.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRole {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean enabled;
    private List<String> roles = new ArrayList<>();
}

package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetailDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled;
    private List<String> roles = new ArrayList<>();
}

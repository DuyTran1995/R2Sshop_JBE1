package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

@Data
public class UserCartDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

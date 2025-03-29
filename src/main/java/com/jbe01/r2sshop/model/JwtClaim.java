package com.jbe01.r2sshop.model;

import com.jbe01.r2sshop.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtClaim {
    private Roles roles;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String sub;
    private Long iat;
    private Long exp;
}

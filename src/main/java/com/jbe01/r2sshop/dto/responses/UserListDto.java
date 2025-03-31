package com.jbe01.r2sshop.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

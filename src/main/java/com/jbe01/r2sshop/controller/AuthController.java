package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.dto.requests.SignInRequestDto;
import com.jbe01.r2sshop.dto.requests.SignUpRequestDto;
import com.jbe01.r2sshop.dto.responses.SignInResponseDto;
import com.jbe01.r2sshop.dto.responses.UserDetailDto;
import com.jbe01.r2sshop.entity.Roles;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.UserMapper;
import com.jbe01.r2sshop.service.RoleService;
import com.jbe01.r2sshop.service.UserRoleService;
import com.jbe01.r2sshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<SuccessResponse<UserDetailDto>> createAuthenticationToken(@RequestBody SignUpRequestDto request) {

        Users users = Users.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .build();

        var user = userService.signUp(users);
        List<String> userRoles = new ArrayList<>();


        for (String role : request.getRoles()) {
            Roles getRoleFromRequest = roleService.findRoleByName(role);

            var addRole = userRoleService.save(getRoleFromRequest, users);

            userRoles.add(addRole.getRoles().getName());
        }

        var mappingToDto = userMapper.toDetailDTO(user);

        return SuccessResponse.of(mappingToDto).toResponseEntity();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SuccessResponse<SignInResponseDto>> signIn(@RequestBody SignInRequestDto requestBody) {
        SignInResponseDto users = userService.signIn(requestBody);

        return SuccessResponse.of(users).toResponseEntity();

    }
}

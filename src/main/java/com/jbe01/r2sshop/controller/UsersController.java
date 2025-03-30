package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.dto.responses.UserResponseDto;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.model.Metadata;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.JwtUtil;
import com.jbe01.r2sshop.util.PaginationUtil;
import com.jbe01.r2sshop.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("")
    @HasRoles({"OPERATOR", "ADMIN"})
    public ResponseEntity<SuccessResponse<List<Users>>> findAll(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts
    ) {
        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(userService.countUsers());

        return SuccessResponse.of(userService.findAll(pageRequest)).toResponseEntity();
    }

    @GetMapping("/profile")
    @HasRoles({"USER", "OPERATOR", "ADMIN"})
    public ResponseEntity<SuccessResponse<UserResponseDto>> findById() {
        String token = requestUtil.getTokenFromRequest();
        Long userId = jwtUtil.extractUserIdFromToken(token);
        Users user = userService.findById(userId);


        var profile = UserResponseDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .enabled(user.isEnabled())
                .roles(user.getUserRoles().stream().map((userRole -> userRole.getRoles().getName())).toList())
                .build();

        return SuccessResponse.of(profile).toResponseEntity();
    }

    @PutMapping("/profile")
    @HasRoles({"OPERATOR", "ADMIN", "USER"})
    public void update(@RequestBody Users user) {

        String token = requestUtil.getTokenFromRequest();
        Long userId = jwtUtil.extractUserIdFromToken(token);

        userService.update(user, userId);
    }

    @DeleteMapping("")
    @HasRoles({"OPERATOR", "ADMIN"})
    public void delete(@RequestParam(name = "userId") long userId) {
        userService.delete(userId);
    }

    @GetMapping("/by-status")
    @HasRoles({"OPERATOR", "ADMIN"})
    public ResponseEntity<SuccessResponse<List<Users>>> findAllByIsEnabled(
            @RequestParam(name = "isEnabled", defaultValue = "true") boolean isEnabled
    ) {
        return SuccessResponse.of(userService.findUsersByIsEnabled(isEnabled)).toResponseEntity();
    }
}

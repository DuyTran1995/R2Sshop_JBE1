package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.model.Metadata;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.PaginationUtil;
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

    @GetMapping("/{id}")
    @HasRoles({"OPERATOR", "ADMIN", "USER"})
    public ResponseEntity<SuccessResponse<Users>> findById(@PathVariable long id) {
        return SuccessResponse.of(userService.findById(id)).toResponseEntity();
    }

    @PutMapping("")
    @HasRoles({"OPERATOR", "ADMIN"})
    public void update(@RequestBody Users user, @RequestParam long id) {
        userService.update(user, id);
    }

    @DeleteMapping("")
    @HasRoles({"OPERATOR", "ADMIN"})
    public void delete(@RequestBody Users user, @RequestParam long id) {
        userService.update(user, id);
    }

    @GetMapping("/by-status")
    @HasRoles({"OPERATOR", "ADMIN"})
    public ResponseEntity<SuccessResponse<List<Users>>> findAllByIsEnabled(
            @RequestParam(name = "isEnabled", defaultValue = "true") boolean isEnabled
    ) {
        return SuccessResponse.of(userService.findUsersByIsEnabled(isEnabled)).toResponseEntity();
    }
}

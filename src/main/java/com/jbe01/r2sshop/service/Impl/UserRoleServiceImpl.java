package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Roles;
import com.jbe01.r2sshop.entity.UserRole;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.repository.UserRoleRepository;
import com.jbe01.r2sshop.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole save(Roles roles, Users users) {

        UserRole userRole = UserRole.builder()
                .roles(roles)
                .users(users)
                .build();

        return userRoleRepository.save(userRole);
    }
}

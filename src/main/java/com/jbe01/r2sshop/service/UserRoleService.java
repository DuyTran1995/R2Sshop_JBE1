package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Roles;
import com.jbe01.r2sshop.entity.UserRole;
import com.jbe01.r2sshop.entity.Users;

public interface UserRoleService {
    public UserRole save(Roles roles, Users users);
}

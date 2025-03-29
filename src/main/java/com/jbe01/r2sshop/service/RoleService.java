package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Roles;

public interface RoleService {
    Roles findRoleByName(String roleName);
}

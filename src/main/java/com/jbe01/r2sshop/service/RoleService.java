package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Roles;

import java.util.List;

public interface RoleService {
    Roles findRoleByName(String roleName);
    List<Roles> getRoles();
}

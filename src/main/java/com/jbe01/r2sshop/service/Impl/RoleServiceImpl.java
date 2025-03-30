package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Roles;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.RoleRepository;
import com.jbe01.r2sshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Roles> getRoles() {
        return roleRepository.findAll();
    }

    public Roles findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new NotFoundException("Role " + roleName + " not found"));
    }
}

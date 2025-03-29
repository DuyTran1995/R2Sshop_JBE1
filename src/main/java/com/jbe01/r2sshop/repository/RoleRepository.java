package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    public Optional<Roles> findByName(String name);
}

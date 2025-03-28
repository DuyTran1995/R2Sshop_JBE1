package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.Users;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);
    List<Users> findUsersByEnabled(boolean enabled);
    int countUsersByEnabled(boolean enabled);
}

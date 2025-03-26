package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);

//    @Query(value = "SELECT u.*, r.name as role_name FROM users u\n" +
//            "                JOIN user_role ur ON u.id = ur.user_id\n" +
//            "                JOIN roles r ON r.id = ur.role_id\n" +
//            "            WHERE u.email = :email", nativeQuery = true)
//    UserRole findRolesByEmail(String email);
}

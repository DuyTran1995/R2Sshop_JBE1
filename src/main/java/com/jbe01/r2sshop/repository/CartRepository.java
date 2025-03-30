package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findByProduct_Id(Long productId);
}

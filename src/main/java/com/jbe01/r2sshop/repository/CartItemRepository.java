package com.jbe01.r2sshop.repository;
import com.jbe01.r2sshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

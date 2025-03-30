package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.dto.requests.CartRequestDto;
import com.jbe01.r2sshop.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> findCartItemsByUserId(Long userId);
    void createCart(CartRequestDto cartItem);
}

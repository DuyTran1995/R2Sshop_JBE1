package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Cart;
import com.jbe01.r2sshop.entity.CartItem;

import java.util.List;

public interface CartService {
    Cart findCartByUser();

    Cart createCart();

    Cart getCart();

    Cart updateCart(List<CartItem> updatedItems);

    void deleteCart();
}

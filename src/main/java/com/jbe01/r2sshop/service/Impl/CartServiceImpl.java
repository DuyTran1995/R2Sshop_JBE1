package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Cart;
import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.CartItemRepository;
import com.jbe01.r2sshop.repository.CartRepository;
import com.jbe01.r2sshop.repository.UserRepository;
import com.jbe01.r2sshop.service.CartService;
import com.jbe01.r2sshop.util.JwtUtil;
import com.jbe01.r2sshop.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final UserRepository usersRepository;
    @Autowired
    private final CartItemRepository cartItemRepository;
    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private JwtUtil jwtUtil;

    Long getCurrentUserId() {
        String token = requestUtil.getTokenFromRequest();
        return jwtUtil.extractUserIdFromToken(token);
    }

    public Cart findCartByUser() {
        var userId = getCurrentUserId();
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found " + userId));

        return cartRepository.findByUser_Id(user.getId()).orElseThrow(() -> new NotFoundException("Cart not found with userId: " + userId));
    }


    public Cart createCart() {
        Long userId = getCurrentUserId();
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Cart> existingCart = cartRepository.findByUser_Id(userId);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }

        Cart cart = Cart.builder()
                .user(user)
                .cartItems(new ArrayList<>())
                .build();

        return cartRepository.save(cart);
    }

    public Cart getCart() {
        Cart cart = this.findCartByUser();

        Hibernate.initialize(cart.getCartItems());
        for (CartItem item : cart.getCartItems()) {
            Hibernate.initialize(item.getProduct());
        }
        return cart;
    }

    public Cart updateCart(List<CartItem> updatedItems) {
        Cart cart = this.findCartByUser();

        cart.getCartItems().clear();
        cartItemRepository.deleteAll(cart.getCartItems());

        updatedItems.forEach(item -> item.setCart(cart));
        cart.getCartItems().addAll(updatedItems);

        return cartRepository.save(cart);
    }

    public void deleteCart() {
        Cart cart = this.findCartByUser();
        cartRepository.delete(cart);
    }
}

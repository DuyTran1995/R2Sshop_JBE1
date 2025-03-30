package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.dto.requests.CartRequestDto;
import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.service.CartService;
import com.jbe01.r2sshop.util.JwtUtil;
import com.jbe01.r2sshop.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@HasRoles({"USER"})
@RequestMapping("/api/v1/carts")
public class CartsController {
    @Autowired
    private CartService cartService;
    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    public void addCart(@RequestBody CartRequestDto cartItem) {
        cartService.createCart(cartItem);
    }

    @GetMapping("/cart-by-user")
    public List<CartItem> findCartItemsByUserId() {

        String token = requestUtil.getTokenFromRequest();
        Long userId = jwtUtil.extractUserIdFromToken(token);

        return cartService.findCartItemsByUserId(userId);
    }
}

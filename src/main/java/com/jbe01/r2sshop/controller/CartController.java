package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.dto.responses.CartResponseDTO;
import com.jbe01.r2sshop.entity.Cart;
import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.CartMapper;
import com.jbe01.r2sshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@HasRoles({"USER"})
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<SuccessResponse<Cart>> createCart() {
        Cart cart = cartService.createCart();
        return SuccessResponse.of(cart).toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<CartResponseDTO>> getCart() {
        Cart cart = cartService.getCart();

        return SuccessResponse.of(cartMapper.toCartResponseDTO(cart)).toResponseEntity();
    }

    @PutMapping
    public ResponseEntity<SuccessResponse<CartResponseDTO>> updateCart(@RequestBody List<CartItem> updatedItems) {
        Cart updatedCart = cartService.updateCart(updatedItems);
        return SuccessResponse.of(cartMapper.toCartResponseDTO(updatedCart)).toResponseEntity();
    }

    @DeleteMapping
    public void deleteCart() {
        cartService.deleteCart();
    }
}

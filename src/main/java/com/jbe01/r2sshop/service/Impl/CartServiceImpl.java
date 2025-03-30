package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.dto.requests.CartRequestDto;
import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.entity.Users;
import com.jbe01.r2sshop.repository.CartRepository;
import com.jbe01.r2sshop.service.CartService;
import com.jbe01.r2sshop.service.ProductsService;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.JwtUtil;
import com.jbe01.r2sshop.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductsService productsService;

    @Autowired
    RequestUtil requestUtil;

    @Autowired
    JwtUtil jwtUtil;

    public List<CartItem> findCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void createCart(CartRequestDto cartItem) {

        String token = requestUtil.getTokenFromRequest();
        Long userId = jwtUtil.extractUserIdFromToken(token);
        Users users = userService.findById(userId);


        Optional<CartItem> foundProductInCart = cartRepository.findByProduct_Id(cartItem.getProductId());

        if (foundProductInCart.isPresent()) {

            int totalQty = foundProductInCart.get().getQuantity() + cartItem.getQuantity();


            foundProductInCart.get().setQuantity(totalQty);

            cartRepository.save(foundProductInCart.get());
            return;
        }

        Products products = productsService.findById(cartItem.getProductId());

        var cart = CartItem.builder()
                .user(users)
                .product(products)
                .quantity(cartItem.getQuantity())
                .build();

        cartRepository.save(cart);
    }
}

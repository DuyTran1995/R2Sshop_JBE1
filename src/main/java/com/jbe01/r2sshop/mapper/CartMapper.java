package com.jbe01.r2sshop.mapper;

import com.jbe01.r2sshop.dto.responses.CartItemDTO;
import com.jbe01.r2sshop.dto.responses.CartResponseDTO;
import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.entity.Cart;
import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(source = "id", target = "cartId")
    CartResponseDTO toCartResponseDTO(Cart cart);

    @Mapping(source = "product", target = "product")
    CartItemDTO toCartItemDTO(CartItem cartItem);

    ProductsResponseDto toProductDTO(Products product);
}

package com.jbe01.r2sshop.mapper;

import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.entity.Products;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    ProductsResponseDto toDto(Products products);
    List<ProductsResponseDto> toListDto(List<Products> products);
}

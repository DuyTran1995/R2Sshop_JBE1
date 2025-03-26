package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesResponseDto {
    private String name;
    private String description;
    private List<ProductsResponseDto> products;
}

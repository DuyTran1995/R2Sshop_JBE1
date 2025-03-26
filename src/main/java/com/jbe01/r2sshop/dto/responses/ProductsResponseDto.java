package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

@Data
public class ProductsResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stockQuantity;
}

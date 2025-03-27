package com.jbe01.r2sshop.dto.requests;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private Double price;
    private String description;
    private Integer stockQuantity;
}

package com.jbe01.r2sshop.dto.responses;

import lombok.Data;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Data
public class OrderItemsDTO {
    private ProductsResponseDto product;
    private Integer quantity;
    private BigDecimal price;
}

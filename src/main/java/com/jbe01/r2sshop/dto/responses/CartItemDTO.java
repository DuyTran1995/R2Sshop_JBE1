package com.jbe01.r2sshop.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {
    private ProductsResponseDto product;
    private Integer quantity;
}

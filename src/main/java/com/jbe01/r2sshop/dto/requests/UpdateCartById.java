package com.jbe01.r2sshop.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartById {
    private Long productId;
    private Integer quantity;
    private Long cartId;
}
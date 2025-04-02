package com.jbe01.r2sshop.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDTO {
    private Long cartId;
    private List<CartItemDTO> items;
    private UserCartDTO user;
}

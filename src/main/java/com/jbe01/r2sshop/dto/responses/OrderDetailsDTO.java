package com.jbe01.r2sshop.dto.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDetailsDTO {
    private UserOrderDetailDTO user;
    private BigDecimal total;
    private List<OrderItemsDTO> orderItems;
}

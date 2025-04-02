package com.jbe01.r2sshop.mapper;

import com.jbe01.r2sshop.dto.responses.OrderDetailsDTO;
import com.jbe01.r2sshop.dto.responses.OrderItemsDTO;
import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.dto.responses.UserOrderDetailDTO;
import com.jbe01.r2sshop.entity.OrderDetails;
import com.jbe01.r2sshop.entity.OrderItems;
import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    OrderDetailsDTO toOrderDetailsDto(OrderDetails orderDetails);

    List<OrderDetailsDTO> toOrderDetailsList(List<OrderDetails> orderDetails);

    OrderItemsDTO toOrderItemsDto(OrderItems orderItems);

    @Mapping(target = "userId", source = "id")
    UserOrderDetailDTO toUserOrderDetailDto(Users users);

    ProductsResponseDto toProductsResponseDto(Products products);

    List<OrderItemsDTO> toOrderItemsList(List<OrderItems> orderItems);
}

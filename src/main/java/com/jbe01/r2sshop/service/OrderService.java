package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.entity.OrderDetails;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {
    List<OrderDetails> getOrdersByUser(PageRequest pageRequest);
    OrderDetails createOrder();
    List<OrderDetails> getOrders(PageRequest pageRequest);
    OrderDetails getOrder(long id);
    void updateOrder(Long orderId, OrderDetails updatedOrder);
    void deleteOrder(long id);
}

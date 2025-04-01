package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.CartItem;
import com.jbe01.r2sshop.entity.OrderDetails;

import java.util.List;

public interface OrderService {
    List<OrderDetails> getOrdersByUser();
    OrderDetails createOrder();
    List<OrderDetails> getOrders();
    OrderDetails getOrder(long id);
    void updateOrder(OrderDetails orderDetails);
    void deleteOrder(long id);
}

package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.entity.OrderDetails;
import com.jbe01.r2sshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @HasRoles({"USER", "ADMIN", "OPERATOR"})
    @GetMapping("/by-user")
    public List<OrderDetails> getOrderByUserId() {
        return orderService.getOrdersByUser();
    }

    @HasRoles({"USER", "ADMIN", "OPERATOR"})
    @PostMapping
    public OrderDetails createOrder() {
        return orderService.createOrder();
    }
}

package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.*;
import com.jbe01.r2sshop.handler.error.BadRequestException;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.CartRepository;
import com.jbe01.r2sshop.repository.OrderRepository;
import com.jbe01.r2sshop.service.CartService;
import com.jbe01.r2sshop.service.OrderService;
import com.jbe01.r2sshop.service.ProductsService;
import com.jbe01.r2sshop.service.UserService;
import com.jbe01.r2sshop.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductsService productsService;

    @Autowired
    UserService userService;

    @Autowired
    RequestUtil requestUtil;

    @Autowired
    CartRepository cartRepository;

    public OrderDetails getOrder(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public List<OrderDetails> getOrdersByUser() {
        Long usersId = requestUtil.getCurrentUserId();

        return orderRepository.findOrderDetailsByUser_Id(usersId);
    }

    public OrderDetails createOrder() {
        Long usersId = requestUtil.getCurrentUserId();
        Cart cartByUser = cartService.findCartByUser();
        Users user = userService.findById(usersId);

        List<CartItem> cartItems = cartByUser.getCartItems();

        if (cartItems == null) {
            throw new NotFoundException("Cart not found");
        }

        OrderDetails order = OrderDetails.builder()
                .user(user)
                .total(BigDecimal.ZERO)
                .build();

        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Products product = cartItem.getProduct();

            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new BadRequestException("Stock quantity exceeds stock limit");
            }

            BigDecimal itemPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal itemTotal = itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemTotal);

            OrderItems orderItem = OrderItems.builder()
                    .orderDetails(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(itemPrice)
                    .build();

            orderItems.add(orderItem);

            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productsService.save(product);

            order.setTotal(total);
            order.setOrderItems(orderItems);

            OrderDetails savedOrder = orderRepository.save(order);

            cartRepository.delete(cartByUser);

            return savedOrder;
        }

        return null;
    }

    public List<OrderDetails> getOrders() {
        return orderRepository.findAll();
    }

    public void updateOrder(OrderDetails order) {
    }

    public void deleteOrder(long id) {

    }
}

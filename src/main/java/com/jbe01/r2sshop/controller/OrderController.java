package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.aspect.HasRoles;
import com.jbe01.r2sshop.dto.responses.OrderDetailsDTO;
import com.jbe01.r2sshop.entity.OrderDetails;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.OrderDetailsMapper;
import com.jbe01.r2sshop.model.Metadata;
import com.jbe01.r2sshop.service.OrderService;
import com.jbe01.r2sshop.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    OrderDetailsMapper orderDetailsMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<OrderDetailsDTO>>> getOrders(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts
    ) {
        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(orderService.countOrders());
        return SuccessResponse.of(orderDetailsMapper.toOrderDetailsList(orderService.getOrders(pageRequest)), metadata).toResponseEntity();
    }

    @HasRoles({"USER"})
    @GetMapping("/by-user")
    public ResponseEntity<SuccessResponse<List<OrderDetailsDTO>>> getOrderByUserId(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts
    ) {
        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(orderService.countOrdersByUser());

        var orders = orderService.getOrdersByUser(pageRequest);

        return SuccessResponse.of(orderDetailsMapper.toOrderDetailsList(orders), metadata).toResponseEntity();
    }

    @HasRoles({"USER", "ADMIN", "OPERATOR"})
    @PostMapping
    public ResponseEntity<SuccessResponse<OrderDetailsDTO>> createOrder() {
        OrderDetails orderDetails = orderService.createOrder();

        return SuccessResponse.of(orderDetailsMapper.toOrderDetailsDto(orderDetails)).toResponseEntity();
    }

    @HasRoles({"ADMIN", "OPERATOR"})
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
    }

    @HasRoles({"ADMIN", "OPERATOR"})
    @PutMapping("/{id}")
    public void updateOrder(@RequestBody OrderDetails order, @PathVariable long id) {
        orderService.updateOrder(id, order);
    }

}

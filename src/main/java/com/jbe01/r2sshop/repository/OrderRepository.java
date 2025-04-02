package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.OrderDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findOrderDetailsByUser_Id(Long userId, PageRequest pageRequest);
}

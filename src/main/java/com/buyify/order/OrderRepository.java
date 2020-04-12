package com.buyify.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Order save(Order order);

    @Transactional(readOnly = true)
    Optional<Order> findById(Long id);

}
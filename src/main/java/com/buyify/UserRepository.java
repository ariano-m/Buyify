package com.buyify;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Order, Long> {

}


package com.buyify.promotion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Promotion findByProductId(Long id);

}


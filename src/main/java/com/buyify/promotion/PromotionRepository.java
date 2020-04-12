package com.buyify.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Promotion save(Promotion promotion);

    @Transactional(readOnly = true)
    Promotion findByProductId(Long id);

}


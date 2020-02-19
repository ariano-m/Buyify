package com.buyify;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByName(String name);
	@Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', ?1,'%'))")
    List<Product> findByNameIsLike(String name);
}


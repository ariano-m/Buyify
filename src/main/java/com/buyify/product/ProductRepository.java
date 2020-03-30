package com.buyify.product;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @CacheEvict(allEntries = true)
    Product save(Product product);

    @Cacheable
    List<Product> findAll();

    @Cacheable
    Optional<Product> findById(Long id);

    @Cacheable
    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', ?1,'%'))")
    List<Product> findByNameIsLike(String name);

}


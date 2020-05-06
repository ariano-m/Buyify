package com.buyify.product;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @CacheEvict(allEntries = true)
    Product save(Product product);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);

    @Transactional(readOnly = true)
    @Cacheable
    List<Product> findAll();

    @Transactional(readOnly = true)
    @Cacheable
    Optional<Product> findById(Long id);

    @Transactional(readOnly = true)
    @Cacheable
    Product findByName(String name);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', ?1,'%'))")
    List<Product> findByNameIsLike(String name);

}


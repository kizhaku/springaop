package org.kizhaku.springaop.repository;

import org.kizhaku.springaop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM products.products LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Product> getAllProductsPaginatedCustom(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT COUNT(*) from products.products", nativeQuery = true)
    int getAllCount();
}

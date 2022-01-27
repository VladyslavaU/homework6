package com.example.homework6.repository;

import com.example.homework6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Boolean existsByName(String name);
    Product findByName(String name);
}

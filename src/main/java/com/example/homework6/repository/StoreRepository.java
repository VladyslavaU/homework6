package com.example.homework6.repository;

import com.example.homework6.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);
    Boolean existsByName(String name);
}

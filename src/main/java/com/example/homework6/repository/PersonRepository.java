package com.example.homework6.repository;

import com.example.homework6.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
}

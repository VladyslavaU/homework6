package com.example.homework6.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private String name;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private String address;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private String phone;
    @ManyToMany
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Exclude
    private Set<Product> products;
}

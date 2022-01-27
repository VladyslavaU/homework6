package com.example.homework6.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String name;
    @EqualsAndHashCode.Exclude
    private Double price;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Store> stores;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Cart> carts;
}

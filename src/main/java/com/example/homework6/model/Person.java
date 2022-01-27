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
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @EqualsAndHashCode.Exclude
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private String phone;
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private String email;
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Cart> carts;
}

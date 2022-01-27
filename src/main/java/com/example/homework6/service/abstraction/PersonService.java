package com.example.homework6.service.abstraction;

import com.example.homework6.model.Cart;
import com.example.homework6.model.Person;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;

import java.util.List;
import java.util.Set;

public interface PersonService {

    Person createPerson(PersonCreateDto personDto);

    Person getPerson(Long id);

    Person updatePerson(Long id, PersonDto personDto);

    void deletePerson(Long id);

    Person addProduct(Long id, String productName, String storeName);

    Person deleteProduct(Long id, String productName, String storeName);

    Person deleteCart(Long id, String storeName);

    Person deleteAllCarts(Long id);

    Set<Cart> getCarts(Long id);

    List<Person> getPersons();
}

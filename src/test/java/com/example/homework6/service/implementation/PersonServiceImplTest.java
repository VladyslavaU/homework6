package com.example.homework6.service.implementation;

import com.example.homework6.model.Cart;
import com.example.homework6.model.Person;
import com.example.homework6.model.Product;
import com.example.homework6.model.Store;
import com.example.homework6.repository.PersonRepository;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.homework6.TestConstants.ADDRESS;
import static com.example.homework6.TestConstants.EMAIL;
import static com.example.homework6.TestConstants.EMAIL_TWO;
import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.LAST_NAME;
import static com.example.homework6.TestConstants.LAST_NAME_TWO;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PHONE;
import static com.example.homework6.TestConstants.PHONE_TWO;
import static com.example.homework6.TestConstants.PRICE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    private PersonCreateDto personCreateDto;
    private PersonDto personUpdateDto;
    private Person person;
    private Product product;
    private Cart cart;

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private ProductServiceImpl productService;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personCreateDto = new PersonCreateDto()
                .setFirstName(NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPhone(PHONE);

        personUpdateDto = new PersonDto()
                .setId(ID)
                .setFirstName(NAME_TWO)
                .setLastName(LAST_NAME_TWO)
                .setPhone(PHONE_TWO)
                .setEmail(EMAIL_TWO);

        person = new Person()
                .setId(ID)
                .setFirstName(NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPhone(PHONE)
                .setCarts(new HashSet<>());

        product = new Product();
        product.setId(ID)
                .setName(NAME)
                .setPrice(PRICE);

        final Set<Product> products = new HashSet<>();
        products.add(product);

        Store store = new Store();
        store.setId(ID)
                .setName(NAME)
                .setAddress(ADDRESS)
                .setPhone(PHONE)
                .setProducts(products);

        cart = new Cart();
        final List<Product> productList = new ArrayList<>();
        productList.add(product);

        cart.setId(ID)
                .setStoreName(NAME)
                .setProducts(productList)
                .setTotal(PRICE)
                .setPerson(person);

        person.getCarts().add(cart);
    }

    @Test
    void createPerson() {
        final Person newPerson = personService.createPerson(personCreateDto);
        assertEquals(personCreateDto.getFirstName(), newPerson.getFirstName());
        assertEquals(personCreateDto.getLastName(), newPerson.getLastName());
        assertEquals(personCreateDto.getEmail(), newPerson.getEmail());
        assertEquals(personCreateDto.getPhone(), newPerson.getPhone());
    }

    @Test
    void getPerson() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));

        final Person actual = personService.getPerson(ID);
        assertEquals(actual.getId(), person.getId());
        assertEquals(actual.getFirstName(), person.getFirstName());
        assertEquals(actual.getLastName(), person.getLastName());
        assertEquals(actual.getPhone(), person.getPhone());
        assertEquals(actual.getEmail(), person.getEmail());
    }

    @Test
    void updatePerson() {
        when(personRepository.existsByEmail(personUpdateDto.getEmail())).thenReturn(false);
        when(personRepository.existsByPhone(personUpdateDto.getPhone())).thenReturn(false);
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));

        final Person actual = personService.updatePerson(ID,personUpdateDto);

        assertEquals(actual.getId(), personUpdateDto.getId());
        assertEquals(actual.getFirstName(), personUpdateDto.getFirstName());
        assertEquals(actual.getLastName(), personUpdateDto.getLastName());
        assertEquals(actual.getPhone(), personUpdateDto.getPhone());
        assertEquals(actual.getEmail(), personUpdateDto.getEmail());
    }

    @Test
    void deletePerson() {
        assertDoesNotThrow(() -> personService.deletePerson(ID));
    }

    @Test
    void addProduct() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));
        when(productService.getProductFromStoreOrThrow(NAME, NAME)).thenReturn(product);
        final Person actual = personService.addProduct(ID, NAME, NAME);

        assertEquals(1, actual.getCarts().size());
        assertTrue(actual.getCarts().contains(cart));
    }

    @Test
    void deleteProduct() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));
        when(productService.getProductFromStoreOrThrow(NAME, NAME)).thenReturn(product);

        personService.deleteProduct(ID, NAME, NAME);
        assertEquals(0, person.getCarts().size());
    }

    @Test
    void deleteCart() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));

        personService.deleteCart(ID, NAME);
        assertEquals(0, person.getCarts().size());
    }

    @Test
    void deleteAllCarts() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));

        personService.deleteAllCarts(ID);

        assertEquals(0, person.getCarts().size());
    }

    @Test
    void getCarts() {
        when(personRepository.findById(ID)).thenReturn(Optional.ofNullable(person));

        assertEquals(1, personService.getCarts(ID).size());
    }

    @Test
    void getPersons() {
        when(personRepository.findAll()).thenReturn(List.of(person));

        assertEquals(1, personService.getPersons().size());
    }
}

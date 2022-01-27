package com.example.homework6.service.implementation;

import com.example.homework6.exception.exists.PersonEmailAlreadyExistsException;
import com.example.homework6.exception.exists.PersonPhoneAlreadyExistsException;
import com.example.homework6.exception.notfound.PersonNotFoundException;
import com.example.homework6.model.Cart;
import com.example.homework6.model.Person;
import com.example.homework6.model.Product;
import com.example.homework6.repository.PersonRepository;
import com.example.homework6.service.abstraction.PersonService;
import com.example.homework6.utils.converters.PersonConverter;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ProductServiceImpl productService;

    @Override
    public Person createPerson(final PersonCreateDto personDto) {
        this.validatePhone(personDto.getPhone());
        this.validateEmail(personDto.getEmail());
        final Person person = PersonConverter.fromDtoCreate(personDto);
        personRepository.save(person);
        return person;
    }

    @Override
    public Person getPerson(final Long id) {
        return this.getPersonOrThrow(id);
    }

    @Override
    public Person updatePerson(final Long id, final PersonDto personDto) {
        Person person = this.getPersonOrThrow(id);
        this.validateUpdatePhoneAndEmail(personDto.getPhone(), personDto.getEmail(), person);
        person = PersonConverter.fromDtoUpdate(personDto, person);
        personRepository.save(person);
        return person;
    }

    @Override
    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person addProduct(final Long id, final String productName, final String storeName) {
        Person person = this.getPersonOrThrow(id);
        final Product product = productService.getProductFromStoreOrThrow(productName, storeName);
        person.getCarts().stream()
                .filter(existingCart -> existingCart.getStoreName().equals(storeName))
                .findFirst()
                .ifPresentOrElse(existingCart -> {
                            existingCart.getProducts().add(product);
                            existingCart.setTotal(existingCart.getTotal() + (product.getPrice()));
                        },
                        () -> {
                            Cart newCart = new Cart()
                                    .setStoreName(storeName)
                                    .setTotal(product.getPrice())
                                    .setPerson(person)
                                    .setProducts(List.of(product));
                            person.getCarts().add(newCart);
                        }
                );
        personRepository.save(person);
        return person;
    }

    @Override
    public Person deleteProduct(final Long id, final String productName, final String storeName) {
        final Person person = this.getPersonOrThrow(id);
        final Product product = productService.getProductFromStoreOrThrow(productName, storeName);
        person.getCarts().stream()
                .filter(cart -> cart.getStoreName().equals(storeName))
                .findFirst()
                .ifPresent(cart -> {
                            if(cart.getProducts().contains(product)) {
                                cart.getProducts().remove(product);
                                cart.setTotal(cart.getTotal() - product.getPrice());
                            }
                            if (cart.getProducts().isEmpty()) {
                                person.getCarts().remove(cart);
                            }
                        }
                );
        Person personUpdated = personRepository.save(person);
        return personUpdated;
    }

    @Override
    public Person deleteCart(final Long id, final String storeName) {
        final Person person = this.getPersonOrThrow(id);
        person.getCarts().stream()
                .filter(cart -> cart.getStoreName().equals(storeName))
                .findFirst().ifPresent(cart -> person.getCarts().remove(cart));
        Person personUpdated = personRepository.save(person);
        return personUpdated;
    }

    @Override
    public Person deleteAllCarts(final Long id) {
        Person person = this.getPersonOrThrow(id);
        person.getCarts().clear();
        person = personRepository.save(person);
        return person;
    }

    @Override
    public Set<Cart> getCarts(final Long id) {
        final Person person = this.getPersonOrThrow(id);
        return person.getCarts();
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    private Person getPersonOrThrow(final Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id.toString()));
    }

    private void validatePhone(final String phone) {
        if (personRepository.existsByPhone(phone)) {
            throw new PersonPhoneAlreadyExistsException(phone);
        }
    }

    private void validateEmail(final String email) {
        if (personRepository.existsByEmail(email)) {
            throw new PersonEmailAlreadyExistsException(email);
        }
    }

    private void validateUpdatePhoneAndEmail(final String phone, final String email, final Person person) {
        if (!phone.equals(person.getPhone())) {
            this.validatePhone(phone);
        }
        if (!email.equals(person.getEmail())) {
            this.validateEmail(email);
        }
    }
}

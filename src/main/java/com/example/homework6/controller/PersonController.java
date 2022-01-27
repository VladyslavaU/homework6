package com.example.homework6.controller;

import com.example.homework6.model.Person;
import com.example.homework6.service.abstraction.PersonService;
import com.example.homework6.utils.converters.CartConverter;
import com.example.homework6.utils.converters.PersonConverter;
import com.example.homework6.utils.dto.CartDto;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getPerson(@RequestParam Long id) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.getPerson(id)));
    }

    @RequestMapping("/all")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getPersons());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonCreateDto person) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.createPerson(person)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> updatePerson(@RequestParam Long id, @Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.updatePerson(id, personDto)));
    }

    @DeleteMapping
    public void deletePerson(@RequestParam Long id) {
        personService.deletePerson(id);
    }

    @RequestMapping("/allcarts")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartDto>> getAllCarts(@RequestParam Long id) {
        return ResponseEntity.ok(CartConverter.toDto(personService.getCarts(id)));
    }

    @RequestMapping("/addproduct")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> addProductToCart(@RequestParam Long id, @RequestParam String productName, @RequestParam String storeName) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.addProduct(id, productName, storeName)));
    }

    @RequestMapping("/deleteproduct")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> deleteProductFromCart(@RequestParam Long id, @RequestParam String productName, @RequestParam String storeName) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.deleteProduct(id, productName, storeName)));
    }

    @RequestMapping("/cart")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> deleteCart(@RequestParam Long id, @RequestParam String storeName) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.deleteCart(id, storeName)));
    }

    @RequestMapping("/carts")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> deleteAllCarts(@RequestParam Long id) {
        return ResponseEntity.ok(PersonConverter.toDto(personService.deleteAllCarts(id)));
    }
}

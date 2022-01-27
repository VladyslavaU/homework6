package com.example.homework6.utils.converters;

import com.example.homework6.model.Person;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static com.example.homework6.TestConstants.EMAIL;
import static com.example.homework6.TestConstants.EMAIL_TWO;
import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.LAST_NAME;
import static com.example.homework6.TestConstants.LAST_NAME_TWO;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PHONE;
import static com.example.homework6.TestConstants.PHONE_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonConverterTest {
    private PersonDto personDto;
    private PersonCreateDto personCreateDto;
    private Person person;

    @BeforeEach
    void setUp() {
        personDto = new PersonDto()
                .setId(ID)
                .setFirstName(NAME_TWO)
                .setLastName(LAST_NAME_TWO)
                .setPhone(PHONE_TWO)
                .setEmail(EMAIL_TWO);
        personCreateDto = new PersonCreateDto()
                .setFirstName(NAME)
                .setLastName(LAST_NAME)
                .setPhone(PHONE)
                .setLastName(LAST_NAME);
        person = new Person()
                .setId(ID)
                .setFirstName(NAME)
                .setLastName(LAST_NAME)
                .setPhone(PHONE)
                .setEmail(EMAIL)
                .setCarts(new HashSet<>());
    }

    @Test
    void fromDtoCreate() {
        final Person actual = PersonConverter.fromDtoCreate(personCreateDto);

        assertEquals(actual.getFirstName(), personCreateDto.getFirstName());
        assertEquals(actual.getLastName(), personCreateDto.getLastName());
        assertEquals(actual.getEmail(), personCreateDto.getEmail());
        assertEquals(actual.getPhone(), personCreateDto.getPhone());
    }

    @Test
    void fromDtoUpdate() {
        final Person personUpdated = PersonConverter.fromDtoUpdate(personDto, person);

        assertEquals(personUpdated.getId(), person.getId());
        assertEquals(personUpdated.getFirstName(), personDto.getFirstName());
        assertEquals(personUpdated.getLastName(), personDto.getLastName());
        assertEquals(personUpdated.getPhone(), personDto.getPhone());
        assertEquals(personUpdated.getEmail(), personDto.getEmail());
    }

    @Test
    void toDto() {
        final PersonDto actual = PersonConverter.toDto(person);

        assertEquals(actual.getFirstName(), person.getFirstName());
        assertEquals(actual.getLastName(), person.getLastName());
        assertEquals(actual.getPhone(), person.getPhone());
        assertEquals(actual.getEmail(), person.getEmail());
    }
}

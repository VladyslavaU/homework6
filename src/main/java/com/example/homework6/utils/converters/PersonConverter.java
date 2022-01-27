package com.example.homework6.utils.converters;

import com.example.homework6.model.Person;
import com.example.homework6.utils.dto.PersonCreateDto;
import com.example.homework6.utils.dto.PersonDto;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PersonConverter {
    public static Person fromDtoCreate(final PersonCreateDto personDto) {
        return new Person()
                .setFirstName(personDto.getFirstName())
                .setLastName(personDto.getLastName())
                .setPhone(personDto.getPhone())
                .setEmail(personDto.getEmail());
    }

    public static Person fromDtoUpdate(final PersonDto personDto, final Person toUpdate){
        return toUpdate
                .setId(toUpdate.getId())
                .setFirstName(Optional.ofNullable(personDto.getFirstName()).orElse(toUpdate.getFirstName()))
                .setLastName(Optional.ofNullable(personDto.getLastName()).orElse(toUpdate.getLastName()))
                .setEmail(Optional.ofNullable(personDto.getEmail()).orElse(toUpdate.getEmail()))
                .setPhone(Optional.ofNullable(personDto.getPhone()).orElse(toUpdate.getPhone()));
    }

    public static PersonDto toDto(final Person person) {
        return new PersonDto()
                .setId(person.getId())
                .setFirstName(person.getFirstName())
                .setLastName(person.getLastName())
                .setPhone(person.getPhone())
                .setEmail(person.getEmail())
                .setCarts(person.getCarts() != null ? person.getCarts().stream().map(CartConverter::toDto).collect(Collectors.toList()) : new ArrayList<>());
    }
}

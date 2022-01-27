package com.example.homework6.exception.notfound;

import static com.example.homework6.utils.Constants.PERSON_NOT_FOUND;

public class PersonNotFoundException extends ResourceNotFoundException {
    public PersonNotFoundException(String name) {
        super(PERSON_NOT_FOUND + name);
    }
}

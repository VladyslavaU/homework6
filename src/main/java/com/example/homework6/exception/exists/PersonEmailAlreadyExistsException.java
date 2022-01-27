package com.example.homework6.exception.exists;

import static com.example.homework6.utils.Constants.PERSON_WITH_EMAIL_EXISTS;

public class PersonEmailAlreadyExistsException extends AlreadyExistsException{
    public PersonEmailAlreadyExistsException(String email) {
        super(PERSON_WITH_EMAIL_EXISTS + email);
    }
}

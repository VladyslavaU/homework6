package com.example.homework6.exception.exists;

import static com.example.homework6.utils.Constants.PERSON_WITH_PHONE_EXISTS;

public class PersonPhoneAlreadyExistsException extends AlreadyExistsException{
    public PersonPhoneAlreadyExistsException(String phone) {
        super(PERSON_WITH_PHONE_EXISTS + phone);
    }
}

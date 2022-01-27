package com.example.homework6.exception.exists;

import static com.example.homework6.utils.Constants.STORE_ALREADY_EXISTS;

public class StoreAlreadyExistsException extends AlreadyExistsException{
    public StoreAlreadyExistsException(String name) {
        super(STORE_ALREADY_EXISTS + name);
    }
}

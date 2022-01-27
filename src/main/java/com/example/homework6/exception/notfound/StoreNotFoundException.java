package com.example.homework6.exception.notfound;

import static com.example.homework6.utils.Constants.STORE_NOT_FOUND;

public class StoreNotFoundException extends ResourceNotFoundException {
    public StoreNotFoundException(String name) {
        super(STORE_NOT_FOUND + name);
    }
}

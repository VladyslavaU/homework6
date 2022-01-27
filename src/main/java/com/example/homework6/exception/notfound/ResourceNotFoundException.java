package com.example.homework6.exception.notfound;

import com.example.homework6.exception.ApplicationException;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

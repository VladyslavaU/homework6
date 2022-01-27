package com.example.homework6.exception.exists;

import com.example.homework6.exception.ApplicationException;

public class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

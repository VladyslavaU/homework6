package com.example.homework6.exception.notfound;

import static com.example.homework6.utils.Constants.PRODUCT_NOT_FOUND_IN_SELECTED_STORE;

public class ProductNotFoundInSelectedStoreException extends ResourceNotFoundException{
    public ProductNotFoundInSelectedStoreException(String name) {
        super(PRODUCT_NOT_FOUND_IN_SELECTED_STORE + name);
    }
}

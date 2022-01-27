package com.example.homework6.service.abstraction;

import com.example.homework6.model.Store;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;

import java.util.List;

public interface StoreService {
    Store createStore(StoreCreateDto storeDto);
    Store updateStore(Long id, StoreDto storeDto);
    void deleteStore(Long id);
    Store getStore(Long id);
    Store addProductToStore(Long id, String productName);
    Store deleteProductFromStore(Long id, String productName);
    List<Store> getStores();
}

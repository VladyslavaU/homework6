package com.example.homework6.service.implementation;

import com.example.homework6.exception.exists.StoreAlreadyExistsException;
import com.example.homework6.exception.notfound.ProductNotFoundException;
import com.example.homework6.exception.notfound.StoreNotFoundException;
import com.example.homework6.model.Product;
import com.example.homework6.model.Store;
import com.example.homework6.repository.ProductRepository;
import com.example.homework6.repository.StoreRepository;
import com.example.homework6.service.abstraction.StoreService;
import com.example.homework6.utils.converters.StoreConverter;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    @Override
    public Store createStore(final StoreCreateDto storeDto) {
        this.validateStoreName(storeDto.getName());
        final Store store =  StoreConverter.fromDtoCreate(storeDto);
        storeRepository.save(store);
        return store;
    }

    @Override
    public Store updateStore(final Long id, final StoreDto storeDto) {
        Store store = this.findStoreByIdOrThrow(id);
        if(!store.getName().equals(storeDto.getName())){
            this.validateStoreName(storeDto.getName());
        }
        store = StoreConverter.fromDtoUpdate(storeDto, store);
        storeRepository.save(store);
        return store;
    }

    @Override
    public void deleteStore(final Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Store getStore(Long id) {
        return findStoreByIdOrThrow(id);
    }

    @Override
    public Store addProductToStore(Long id, String productName) {
        Store store = this.findStoreByIdOrThrow(id);
        this.checkProductByNameOrThrow(productName);
        final Product product = productRepository.findByName(productName);
        store.getProducts().add(product);
        store = storeRepository.save(store);
        return store;
    }

    @Override
    public Store deleteProductFromStore(Long id, String productName) {
        Store store = this.findStoreByIdOrThrow(id);
        this.checkProductByNameOrThrow(productName);
        final Product product = productRepository.findByName(productName);
        store.getProducts().remove(product);
        store = storeRepository.save(store);
        return store;
    }

    @Override
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    protected Store findStoreByNameOrThrow(final String storeName) {
        final Store store = storeRepository.findByName(storeName);
        if (store == null) {
            throw new StoreNotFoundException(storeName);
        }
        return store;
    }

    private Store findStoreByIdOrThrow(final Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new StoreNotFoundException(id.toString()));
    }

    private void validateStoreName(final String storeName) {
        if (storeRepository.existsByName(storeName)) {
            throw new StoreAlreadyExistsException(storeName);
        }
    }

    private void checkProductByNameOrThrow(final String name){
        if(!productRepository.existsByName(name)){
            throw new ProductNotFoundException(name);
        }
    }
}

package com.example.homework6.service.implementation;

import com.example.homework6.exception.notfound.StoreNotFoundException;
import com.example.homework6.model.Product;
import com.example.homework6.model.Store;
import com.example.homework6.repository.ProductRepository;
import com.example.homework6.repository.StoreRepository;
import com.example.homework6.utils.dto.StoreCreateDto;
import com.example.homework6.utils.dto.StoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.example.homework6.TestConstants.ADDRESS;
import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PHONE;
import static com.example.homework6.TestConstants.PRICE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {
    private StoreCreateDto storeCreateDto;
    private StoreDto storeUpdateDto;
    private Store store;
    private Product product;

    @InjectMocks
    private StoreServiceImpl storeService;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        storeCreateDto = new StoreCreateDto()
                .setName(NAME)
                .setAddress(ADDRESS)
                .setPhone(PHONE);
        storeUpdateDto = new StoreDto()
                .setName(NAME_TWO);
        store = new Store()
                .setName(NAME)
                .setAddress(ADDRESS)
                .setPhone(PHONE)
                .setProducts(new HashSet<>());
        product = new Product()
                .setName(NAME)
                .setPrice(PRICE);
    }

    @Test
    void createStore() {
        final Store actual = storeService.createStore(storeCreateDto);

        assertEquals(actual.getName(), storeCreateDto.getName());
        assertEquals(actual.getAddress(), storeCreateDto.getAddress());
        assertEquals(actual.getPhone(), storeCreateDto.getPhone());
    }

    @Test
    void updateStore() {
        when(storeRepository.findById(ID)).thenReturn(Optional.ofNullable(store));
        final Store storeUpdated = storeService.updateStore(ID, storeUpdateDto);

        assertEquals(storeUpdateDto.getName(), storeUpdated.getName());
    }

    @Test
    void deleteStore() {
        assertDoesNotThrow(() -> storeService.deleteStore(ID));
    }

    @Test
    void getStore() {
        when(storeRepository.findById(ID)).thenReturn(Optional.ofNullable(store));

        final Store actual = storeService.getStore(ID);

        assertEquals(actual.getName(), store.getName());
        assertEquals(actual.getPhone(), store.getPhone());
        assertEquals(actual.getAddress(), store.getAddress());
    }

    @Test
    void addProductToStore() {
        when(storeRepository.findById(ID)).thenReturn(Optional.ofNullable(store));
        when(productRepository.existsByName(NAME)).thenReturn(true);
        when(productRepository.findByName(NAME)).thenReturn(product);

        storeService.addProductToStore(ID, NAME);

        assertEquals(1, store.getProducts().size());
        assertTrue(store.getProducts().contains(product));
    }

    @Test
    void deleteProductFromStore() {
        store.getProducts().add(product);

        when(storeRepository.findById(ID)).thenReturn(Optional.ofNullable(store));
        when(productRepository.existsByName(NAME)).thenReturn(true);
        when(productRepository.findByName(NAME)).thenReturn(product);

        storeService.deleteProductFromStore(ID, NAME);

        assertEquals(0, store.getProducts().size());
        assertFalse(store.getProducts().contains(product));
    }

    @Test
    void getStores() {
        when(storeRepository.findAll()).thenReturn(List.of(store));

        assertEquals(storeService.getStores().size(), 1);
    }

    @Test
    void findStoreByNameOrThrow() {
        when(storeRepository.findByName(NAME)).thenReturn(store);

        assertDoesNotThrow(() -> storeService.findStoreByNameOrThrow(NAME));

        final Exception exception = assertThrows(StoreNotFoundException.class, () -> storeService.findStoreByNameOrThrow(NAME_TWO));
        assertEquals(exception.getMessage(), new StoreNotFoundException(NAME_TWO).getMessage());
    }
}

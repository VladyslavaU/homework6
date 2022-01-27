package com.example.homework6.service.implementation;

import static com.example.homework6.TestConstants.ADDRESS;
import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PHONE;
import static com.example.homework6.TestConstants.PRICE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.homework6.exception.notfound.ProductNotFoundException;
import com.example.homework6.exception.notfound.ProductNotFoundInSelectedStoreException;
import com.example.homework6.model.Product;
import com.example.homework6.model.Store;
import com.example.homework6.repository.ProductRepository;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private ProductCreateDto productCreateDto;
    private ProductDto productDto;
    private Product product;
    private Store store;
    private Exception exception;

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private StoreServiceImpl storeService;

    @BeforeEach
    void setUp() {
        productCreateDto = new ProductCreateDto()
                .setName(NAME)
                .setPrice(PRICE);
        productDto = new ProductDto()
                .setName(NAME_TWO)
                .setPrice(PRICE);
        product = new Product()
                .setName(NAME)
                .setPrice(PRICE);
        store = new Store()
                .setName(NAME)
                .setAddress(ADDRESS)
                .setPhone(PHONE)
                .setProducts(new HashSet<>());
        store.getProducts().add(product);
    }

    @Test
    void createProduct() {
        final Product product = productService.createProduct(productCreateDto);

        assertEquals(product.getName(), productCreateDto.getName());
        assertEquals(product.getPrice(), productCreateDto.getPrice());
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
        final Product productUpdated = productService.updateProduct(ID, productDto);

        assertEquals(productUpdated.getName(), productDto.getName());
        assertEquals(productUpdated.getPrice(), productDto.getPrice());
    }

    @Test
    void getProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));

        final Product actual = productService.getProduct(ID);

        assertEquals(actual.getName(), product.getName());
        assertEquals(actual.getPrice(), product.getPrice());
    }

    @Test
    void deleteProduct() {
        assertDoesNotThrow(() -> productService.deleteProduct(ID));
    }

    @Test
    void getProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void getProductByNameOrThrow() {
        when(productRepository.findByName(NAME)).thenReturn(product);
        assertDoesNotThrow(() -> productService.getProductByNameOrThrow(NAME));

        exception = assertThrows(ProductNotFoundException.class, () -> productService.getProductByNameOrThrow(NAME_TWO));
        assertEquals(exception.getMessage(), new ProductNotFoundException(NAME_TWO).getMessage());
    }

    @Test
    void getProductFromStoreOrThrow() {
        when(storeService.findStoreByNameOrThrow(NAME)).thenReturn(store);
        assertDoesNotThrow(() -> productService.getProductFromStoreOrThrow(NAME, NAME));

        exception = assertThrows(ProductNotFoundInSelectedStoreException.class, () -> productService.getProductFromStoreOrThrow(NAME_TWO, NAME));
        assertEquals(exception.getMessage(), new ProductNotFoundInSelectedStoreException(NAME_TWO).getMessage());
    }
}

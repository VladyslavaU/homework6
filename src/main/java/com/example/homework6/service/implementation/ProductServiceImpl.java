package com.example.homework6.service.implementation;

import com.example.homework6.exception.exists.ProductAlreadyExistsException;
import com.example.homework6.exception.notfound.ProductNotFoundException;
import com.example.homework6.exception.notfound.ProductNotFoundInSelectedStoreException;
import com.example.homework6.model.Product;
import com.example.homework6.model.Store;
import com.example.homework6.repository.ProductRepository;
import com.example.homework6.service.abstraction.ProductService;
import com.example.homework6.utils.converters.ProductConverter;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final StoreServiceImpl storeService;

    @Override
    public Product createProduct(final ProductCreateDto dto) {
        this.validateProductName(dto.getName());
        Product product = ProductConverter.fromDtoCreate(dto);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(final Long id, final ProductDto dto) {
        Product product = this.getProductByIdOrThrow(id);
        if (!product.getName().equals(dto.getName())) {
            this.validateProductName(dto.getName());
        }
        product = ProductConverter.fromDtoUpdate(dto, product);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProduct(final Long id) {
        return this.getProductByIdOrThrow(id);
    }

    @Override
    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    protected Product getProductByNameOrThrow(final String productName){
        final Product product = productRepository.findByName(productName);
        if(product != null){
            return product;
        }
        throw new ProductNotFoundException(productName);
    }

    private void validateProductName(String name) {
        if (productRepository.existsByName(name)) {
            throw new ProductAlreadyExistsException(name);
        }
    }

    private Product getProductByIdOrThrow(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    protected Product getProductFromStoreOrThrow(final String productName, final String storeName){
        final Store store = storeService.findStoreByNameOrThrow(storeName);
        return store.getProducts().stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundInSelectedStoreException(productName));
    }
}

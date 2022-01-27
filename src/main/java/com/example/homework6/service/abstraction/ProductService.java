package com.example.homework6.service.abstraction;

import com.example.homework6.model.Product;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductCreateDto dto);

    Product updateProduct(Long id, ProductDto productDto);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    List<Product> getProducts();
}

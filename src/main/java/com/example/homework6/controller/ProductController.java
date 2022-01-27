package com.example.homework6.controller;

import com.example.homework6.service.abstraction.ProductService;
import com.example.homework6.utils.converters.ProductConverter;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping("/all")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(ProductConverter.toDto(productService.getProducts()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProduct(@RequestParam Long id) {
        return ResponseEntity.ok(ProductConverter.toDto(productService.getProduct(id)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestParam Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(ProductConverter.toDto(productService.updateProduct(id, productDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductCreateDto productDto) {
        return ResponseEntity.ok(ProductConverter.toDto(productService.createProduct(productDto)));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}

package com.example.homework6.utils.converters;

import com.example.homework6.model.Product;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ProductConverter {
    public static ProductDto toDto(final Product product){
        return new ProductDto()
                .setId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice());
    }

    public static List<ProductDto> toDto(final List<Product> products){
        return products.stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }
    public static Product fromDtoCreate(final ProductCreateDto productDto){
        return new Product()
                .setName(productDto.getName())
                .setPrice(productDto.getPrice());
    }

    public static Product fromDtoUpdate(final ProductDto productDto, final Product toUpdate){
        return toUpdate
                .setId(toUpdate.getId())
                .setName(Optional.ofNullable(productDto.getName()).orElse(toUpdate.getName()))
                .setPrice(Optional.ofNullable(productDto.getPrice()).orElse(toUpdate.getPrice()));
    }
}

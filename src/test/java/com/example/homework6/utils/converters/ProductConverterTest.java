package com.example.homework6.utils.converters;

import com.example.homework6.model.Product;
import com.example.homework6.utils.dto.ProductCreateDto;
import com.example.homework6.utils.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.NAME_TWO;
import static com.example.homework6.TestConstants.PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductConverterTest {
    private Product product;
    private ProductCreateDto productCreateDto;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product()
                .setName(NAME)
                .setPrice(PRICE);

        productCreateDto = new ProductCreateDto()
                .setName(NAME)
                .setPrice(PRICE);

        productDto = new ProductDto()
                .setName(NAME_TWO)
                .setPrice(PRICE);
    }

    @Test
    void toDto() {
        final ProductDto actual = ProductConverter.toDto(product);

        assertEquals(actual.getName(), product.getName());
        assertEquals(actual.getPrice(), product.getPrice());
    }

    @Test
    void testToDto() {
        final List<ProductDto> productDtos = ProductConverter.toDto(List.of(product));
        final ProductDto actual = productDtos.get(0);

        assertEquals(productDtos.size(), 1);
        assertEquals(actual.getName(), product.getName());
        assertEquals(actual.getPrice(), product.getPrice());
    }

    @Test
    void fromDtoCreate() {
        final Product actual = ProductConverter.fromDtoCreate(productCreateDto);

        assertEquals(actual.getName(), productCreateDto.getName());
        assertEquals(actual.getPrice(), productCreateDto.getPrice());
    }

    @Test
    void fromDtoUpdate() {
        final Product actual = ProductConverter.fromDtoUpdate(productDto, product);

        assertEquals(actual.getName(), productDto.getName());
        assertEquals(actual.getPrice(), productDto.getPrice());
    }
}

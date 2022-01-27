package com.example.homework6.utils.converters;

import com.example.homework6.model.Cart;
import com.example.homework6.model.Product;
import com.example.homework6.utils.dto.CartDto;
import com.example.homework6.utils.dto.ProductQuantityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.example.homework6.TestConstants.ID;
import static com.example.homework6.TestConstants.NAME;
import static com.example.homework6.TestConstants.PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartConverterTest {
    private Cart cart;
    private Product product;
    private ProductQuantityDto productQuantityDto;

    @BeforeEach
    void setUp() {
        product = new Product()
                .setId(ID)
                .setName(NAME)
                .setPrice(PRICE);
        cart = new Cart()
                .setTotal(PRICE)
                .setStoreName(NAME)
                .setProducts(List.of(product));
    }

    @Test
    void toDto() {
        final CartDto cartDto = CartConverter.toDto(cart);
        productQuantityDto = cartDto.getProducts().get(0);

        assertEquals(cartDto.getStoreName(), cart.getStoreName());
        assertEquals(cartDto.getProducts().size(), 1);
        assertEquals(cartDto.getTotal(), cart.getTotal());
        assertEquals(productQuantityDto.getProductName(), product.getName());
        assertEquals(productQuantityDto.getQuantity(), 1);
        assertEquals(productQuantityDto.getTotal(), PRICE);
        assertEquals(productQuantityDto.getPrice(), PRICE);
    }

    @Test
    void testToDto() {
        final List<CartDto> cartDtos = CartConverter.toDto(Set.of(cart));
        final CartDto cartDto = cartDtos.get(0);
        productQuantityDto = cartDto.getProducts().get(0);

        assertEquals(cartDtos.size(), 1);
        assertEquals(cartDto.getStoreName(), cart.getStoreName());
        assertEquals(cartDto.getProducts().size(), 1);
        assertEquals(cartDto.getTotal(), cart.getTotal());
        assertEquals(productQuantityDto.getProductName(), product.getName());
        assertEquals(productQuantityDto.getQuantity(), 1);
        assertEquals(productQuantityDto.getTotal(), PRICE);
        assertEquals(productQuantityDto.getPrice(), PRICE);
    }
}

package com.example.homework6.utils.converters;

import com.example.homework6.model.Cart;
import com.example.homework6.model.Product;
import com.example.homework6.utils.dto.CartDto;
import com.example.homework6.utils.dto.ProductQuantityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class CartConverter {
    public static CartDto toDto(final Cart cart) {
        return new CartDto()
                .setStoreName(cart.getStoreName())
                .setTotal(cart.getTotal())
                .setProducts(organizeCart(cart.getProducts()));
    }

    public static List<CartDto> toDto(final Set<Cart> carts){
        return carts.stream()
                .map(CartConverter::toDto)
                .collect(Collectors.toList());
    }

    private static List<ProductQuantityDto> organizeCart(final List<Product> products) {
        final Map<Product, Long> productQuantity =
                products.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        final List<ProductQuantityDto> productQuantityDtoList = new ArrayList<>();
        for (Map.Entry<Product, Long> entry : productQuantity.entrySet()) {
            ProductQuantityDto productQuantityDto = new ProductQuantityDto()
                    .setProductName(entry.getKey().getName())
                    .setQuantity(entry.getValue())
                    .setPrice(entry.getKey().getPrice())
                    .setTotal(entry.getValue() * entry.getKey().getPrice());
            productQuantityDtoList.add(productQuantityDto);
        }
        return productQuantityDtoList;
    }
}

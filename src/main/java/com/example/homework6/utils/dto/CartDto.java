package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class CartDto {
    private Double total;
    private String storeName;
    private List<ProductQuantityDto> products;
}

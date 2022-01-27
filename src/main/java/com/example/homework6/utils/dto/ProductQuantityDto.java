package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class ProductQuantityDto {
    private String productName;
    private Long quantity;
    private Double price;
    private Double total;
}

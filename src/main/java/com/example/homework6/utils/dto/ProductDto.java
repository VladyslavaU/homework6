package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class ProductDto {
    private Long id;
    private String name;
    @NumberFormat
    @Positive
    private Double price;
}

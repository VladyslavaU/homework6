package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ProductCreateDto {
    @NotEmpty
    private String name;
    @NumberFormat
    @Positive
    private Double price;
}

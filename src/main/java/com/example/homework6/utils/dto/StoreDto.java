package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain=true)
public final class StoreDto {
    private Long id;
    private String name;
    private String address;
    @NumberFormat
    private String phone;
    private List<ProductDto> productDtos;
}

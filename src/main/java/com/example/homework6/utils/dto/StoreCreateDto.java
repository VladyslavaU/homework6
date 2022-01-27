package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StoreCreateDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    @NumberFormat
    private String phone;
}

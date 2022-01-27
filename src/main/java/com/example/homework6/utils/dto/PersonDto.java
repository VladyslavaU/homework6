package com.example.homework6.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public final class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    @NumberFormat
    private String phone;
    @Email
    private String email;
    private List<CartDto> carts;
}

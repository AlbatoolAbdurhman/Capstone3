package com.example.capstone3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestorDTO {


    private Integer id;

    @NotEmpty(message = "Name can't be Empty")
    @Size(min = 5, message = "The name length should be at least 5 characters")
    private String name;

    @NotEmpty(message = "Email can't be Empty")
    @Email(message = "Must be a valid email")
    private String email;

    @NotEmpty(message = "Phone number can't be Empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String phoneNumber;
}

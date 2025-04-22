package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Investor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer InvestorId;

    @Column(nullable = false)
    @NotEmpty(message = "name cant be empty")
    @Size(min = 5,message = "the name length should be 5 letters at lest ")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Email cant be empty")
    @Email(message = "must be valid email")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "password cant be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must contain upper, lower, number, special character and at least 8 characters")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "phone number is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String phoneNumber;



    @OneToOne
    private Contract contract;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "investor")
    private Set<Offer> offer;


}

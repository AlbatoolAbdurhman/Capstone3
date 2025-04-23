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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerId;

    @Column(nullable = false)
    @NotEmpty(message="name cant be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message="email cant be empty")
    @Email(message = "must be valid email")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message="password cant be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must contain upper, lower, number, special character and at least 8 characters")    private String password;


    @Column(nullable = false)
    @NotEmpty(message="phone number can't be empty")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must start with 0 and be exactly 10 digits")
    private String phoneNumber;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Contract> contract;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Property> properties;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Offer> offers;
}

package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyId;


    @Column(nullable = false)
    @NotEmpty(message = "Title cannot be Empty")
    private String title;


    @Column(nullable = false)
    @NotEmpty(message = "Description cannot be Empty")
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = "type cannot be blank")
    private String type;

    @NotEmpty(message = "location cannot be blank")
    @Column(nullable = false)
    private String location;


    @Column(nullable = false)
    @NotNull(message = "Area size cannot be null")
    @Positive(message = "Area size must be positive")
    private Double areaSize;


    private LocalDateTime CreateAt;


    @Pattern(regexp = "Active|Inactive")
    private String status = "Inactive";

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "property")
    private Set<Offer> offer;

    @ManyToOne
    @JsonIgnore
    private Owner owner;

}

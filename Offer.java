package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offerId;


    @Column(nullable = false)
    @NotNull(message = "Proposed cost cannot be null")
    @Positive(message = "Proposed cost must be positive")
    private Integer proposedCost;

    @Column(nullable = false)
    @NotNull(message = "Proposed years cannot be null")
    @Positive(message = "Proposed years must be positive")
    private Integer proposedYears;

    @Column(nullable = false)
    @NotEmpty(message = "additional Term can not be empty")
    @Size(min = 5, max = 200, message = "Additional term must be between 5 and 200 characters")
    private String additionalTerm;

    @Pattern(regexp = "Accepted|Unacceptable", message = "Offer status must be either 'Accepted' or 'Unacceptable'")
    private String offerStatus = "Unacceptable";

    @Column(nullable = false)
    @NotNull(message = "Order date cannot be null")
    private LocalDate orderDate;

    @OneToOne(mappedBy = "offer")
    @JsonIgnore
    private Contract contract;



    @ManyToOne
    @JsonIgnore
    private Property property;


    @ManyToOne
    @JsonIgnore
    private Owner owner;

    @ManyToOne
    @JsonIgnore
    private Investor investor;

}

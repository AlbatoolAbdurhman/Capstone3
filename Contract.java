package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ContractId;

    @Column(nullable = false)
    @NotEmpty(message = "Contract documentation path cannot be empty")
    private String ContractDocumentationPath;

    @Column(nullable = false)
    @NotNull(message = "Agreed cost cannot be null")
    @Positive(message = "Agreed cost must be positive")
    private Integer agreeCost;

    @Column(nullable = false)
    @NotNull(message = "Using years cannot be null")
    @Min(value = 1, message = "Using years must be at least 1")
    private Integer usingYears;

    @Column(nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @Column(nullable = false)
    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    private LocalDate paymentDate;

    private LocalDateTime ContractDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Offer offer;
}

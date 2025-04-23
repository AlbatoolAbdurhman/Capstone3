package com.example.capstone3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalRequirement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MunicipalRequirementId ;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "MunicipalRequirement")
    private Set<Property> property;
}

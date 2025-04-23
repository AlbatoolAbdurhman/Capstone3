package com.example.capstone3.Repository;

import com.example.capstone3.Model.MunicipalRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalRequirementRepository extends JpaRepository<MunicipalRequirement, Integer> {

    MunicipalRequirement findMunicipalRequirementByMunicipalRequirementId(Integer municipalRequirementId);
}

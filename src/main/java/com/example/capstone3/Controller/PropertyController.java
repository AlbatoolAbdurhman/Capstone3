package com.example.capstone3.Controller;

import com.example.capstone3.DTO.PropertyRecommendationDTO;
import com.example.capstone3.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/recommendation/{investorId}")
    public ResponseEntity<List<PropertyRecommendationDTO>> recommendProperties(@PathVariable Integer investorId) {
        return ResponseEntity.ok(propertyService.getRecommendedProperties(investorId));
    }
}

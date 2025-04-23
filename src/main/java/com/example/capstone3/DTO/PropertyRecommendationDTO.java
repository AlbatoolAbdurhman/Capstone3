package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyRecommendationDTO {

    private Integer propertyId;
    private String title;
    private String location;
    private String type;
    private Double areaSize;
    private String status;
}

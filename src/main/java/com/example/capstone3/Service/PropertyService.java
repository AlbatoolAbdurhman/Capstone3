package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.PropertyRecommendationDTO;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.InvestorRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PropertyService {


    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final InvestorRepository investorRepository;
    private final OfferRepository offerRepository;


    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    public void addProperty(Property property){
        Owner owner = ownerRepository.findOwnerByOwnerId(property.getPropertyId());
        if(owner == null){
            throw new ApiException("owner required to add property");
        }

        propertyRepository.save(property);

    }


    public void updateProperty(Integer propertyId, Property property){
        Property oldProperty = propertyRepository.findPropertyByPropertyId(propertyId);
        if(oldProperty == null){
            throw new ApiException("Property not found");
        }
        oldProperty.setAreaSize(property.getAreaSize());
        oldProperty.setDescription(property.getDescription());
        oldProperty.setLocation(property.getLocation());
        oldProperty.setType(property.getType());
        oldProperty.setTitle(property.getTitle());

        propertyRepository.save(oldProperty);
    }



    public void deleteProperty(Integer propertyId){
        Property property = propertyRepository.findPropertyByPropertyId(propertyId);
        if(property == null){
            throw new ApiException("Property not found");
        }

        propertyRepository.delete(property);
    }

    public List<PropertyRecommendationDTO> getRecommendedProperties(Integer investorId) {
        Investor investor = investorRepository.findInvestorByInvestorId(investorId);
        if(investor == null){
                throw new ApiException("Investor not found");}

        // 1. Get previous accepted offers
        List<Offer> previousOffers = offerRepository.findByInvestorAndOfferStatus(investor, "Accepted");

        Set<String> preferredTypes = new HashSet<>();
        Set<String> preferredLocations = new HashSet<>();
        Set<Integer> previouslyOfferedPropertyIds = new HashSet<>();

        for (Offer offer : previousOffers) {
            Property property = offer.getProperty();
            if (property != null) {
                preferredTypes.add(property.getType());
                preferredLocations.add(property.getLocation());
                previouslyOfferedPropertyIds.add(property.getPropertyId());
            }
        }

        // 2. Get active properties
        List<Property> allProperties = propertyRepository.findByStatus("Active");

        List<PropertyRecommendationDTO> recommended = new ArrayList<>();

        for (Property property : allProperties) {
            if (previouslyOfferedPropertyIds.contains(property.getPropertyId())) {
                continue;
            }

            if (preferredTypes.contains(property.getType()) || preferredLocations.contains(property.getLocation())) {
                PropertyRecommendationDTO dto = new PropertyRecommendationDTO(property.getPropertyId(), property.getTitle(), property.getLocation(),
                        property.getType(), property.getAreaSize(), property.getStatus());
                recommended.add(dto);
            }}

        return recommended;
    }



}

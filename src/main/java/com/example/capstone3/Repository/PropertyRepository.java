package com.example.capstone3.Repository;

import com.example.capstone3.Model.Investor;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Property findPropertyByPropertyId(Integer propertyId);

    List<Property> findByStatus(String status);
}

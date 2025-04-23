package com.example.capstone3.Repository;

import com.example.capstone3.Model.Investor;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    Offer findOfferByOfferId(Integer offerId);

    //best Offer
    List<Offer>findByPropertyAndOfferStatus(Property property, String offerStatus);

    // 3
    List<Offer> findByInvestorAndOfferStatus(Investor investor, String offerStatus);


}

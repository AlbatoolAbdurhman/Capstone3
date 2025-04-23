package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.BestOfferDTO;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.InvestorRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {


    private final InvestorRepository investorRepository;
    private final PropertyRepository propertyRepository;
    private final OfferRepository offerRepository;


    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void updateOffer(Offer offer, Integer offerId) {
        Offer oldOffer = offerRepository.findOfferByOfferId(offerId);

        if (oldOffer == null) {
            throw new ApiException("Offer Not found!!");
        }

        oldOffer.setOfferStatus(offer.getOfferStatus());
        oldOffer.setInvestor(offer.getInvestor());
        oldOffer.setProperty(offer.getProperty());
        oldOffer.setOfferStatus(offer.getOfferStatus());
        oldOffer.setOrderDate(offer.getOrderDate());
        oldOffer.setProposedCost(offer.getProposedCost());
        oldOffer.setProposedYears(offer.getProposedYears());

        offerRepository.save(oldOffer);
    }

    public void deleteOffer(Integer offerId) {
        Offer offer = offerRepository.findOfferByOfferId(offerId);

        if (offer == null) {
            throw new ApiException("Offer Not found!!");
        }
        offerRepository.delete(offer);
    }

    public void assignInvestorToOffer(Integer offerId, Integer investorId) {
        Offer offer = offerRepository.findOfferByOfferId(offerId);
        Investor investor = investorRepository.findInvestorByInvestorId(investorId);

        if (offer == null) throw new ApiException("Offer not found");
        if (investor == null) throw new ApiException("Investor not found");

        offer.setInvestor(investor);
        offerRepository.save(offer);
    }

    public void assignPropertyToOffer(Integer offerId, Integer propertyId) {
        Offer offer = offerRepository.findOfferByOfferId(offerId);
        Property property = propertyRepository.findPropertyByPropertyId(propertyId);

        if (offer == null) {
            throw new ApiException("Offer not found");
        }
        if (property == null) {
            throw new ApiException("Property not found");
        }

        offer.setProperty(property);
        offerRepository.save(offer);
    }

    public BestOfferDTO getBestOffer(Integer propertyId) {
        Property property = propertyRepository.findPropertyByPropertyId(propertyId);
        if (property == null) throw new ApiException("Property not found");


        List<Offer> offers = offerRepository.findByPropertyAndOfferStatus(property, "Accepted");

        if (offers.isEmpty()) {
            throw new ApiException("No accepted offers available for this property.");
        }

        Offer bestOffer = null;

        for (Offer offer : offers) {
            if (bestOffer == null) {
                bestOffer = offer;
            } else {
                // إذا العرض الحالي أكبر من الحالي من حيث cost، نحدثه
                if (offer.getProposedCost() > bestOffer.getProposedCost()) {
                    bestOffer = offer;
                }
                // إذا التكاليف متساوية، نختار أقل مدة
                else if (offer.getProposedCost().equals(bestOffer.getProposedCost())
                        && offer.getProposedYears() < bestOffer.getProposedYears()) {
                    bestOffer = offer;
                }}

        }

        if (bestOffer == null) {
            throw new ApiException("No valid offer found.");
        }

        return new BestOfferDTO(bestOffer.getOfferId(), bestOffer.getProposedCost(), bestOffer.getProposedYears(), bestOffer.getAdditionalTerm(),
                bestOffer.getOfferStatus(), bestOffer.getOrderDate(), bestOffer.getInvestor().getInvestorId());
    }
}

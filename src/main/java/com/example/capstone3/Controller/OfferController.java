package com.example.capstone3.Controller;

import com.example.capstone3.DTO.BestOfferDTO;
import com.example.capstone3.Service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    @GetMapping("/best-offer/{propertyId}")
    public ResponseEntity<BestOfferDTO> getBestOffer(@PathVariable Integer propertyId) {
        BestOfferDTO bestOffer = offerService.getBestOffer(propertyId);
        return ResponseEntity.ok(bestOffer);
    }

}

package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.OfferDTO;
import com.isa.pharmacies_system.domain.pharmacy.Offer;

public interface IOfferService {
	void saveOffer(OfferDTO offerDTO);
	List<Offer> getAllMyOffers(String userEmail);
	Offer getById(long id);
	boolean checkPossibilitiesOfChangeOffer(long offerId);
}

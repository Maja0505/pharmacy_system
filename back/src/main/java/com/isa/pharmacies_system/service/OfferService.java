package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.OfferDTO;
import com.isa.pharmacies_system.domain.pharmacy.Offer;
import com.isa.pharmacies_system.domain.pharmacy.StateOfOffer;
import com.isa.pharmacies_system.repository.IOfferRepository;
import com.isa.pharmacies_system.repository.IOrderRepository;
import com.isa.pharmacies_system.repository.ISupplierRepository;
import com.isa.pharmacies_system.service.iService.IOfferService;

@Service
public class OfferService implements IOfferService{
	
	private IOfferRepository iOfferRepository;
	private IOrderRepository iOrderRepository;
	private ISupplierRepository iSupplierRepository;
	
	
	@Autowired
	public OfferService(IOfferRepository iOfferRepository,IOrderRepository iOrderRepository,ISupplierRepository iSupplierRepository) {
		this.iOfferRepository = iOfferRepository;
		this.iOrderRepository = iOrderRepository;
		this.iSupplierRepository = iSupplierRepository;
	}
	//zbog Supplier-a i 
	private Offer convertOfferDTOToOffer(OfferDTO offerDTO) {
		Offer offer = new Offer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		if (offerDTO.getId() != -1)
			offer.setId(offerDTO.getId());
		offer.setDeliveryDate(LocalDateTime.parse(offerDTO.getDeliveryDate(), formatter));
		offer.setFullPrice(offerDTO.getFullPrice());
		offer.setStateOfOffer(StateOfOffer.Waiting);
		offer.setOrderForOffer(iOrderRepository.findById(offerDTO.getOrderId()).orElse(null));
		offer.setOfferSupplier(iSupplierRepository.findById(offerDTO.getSupplierId()).orElse(null));
		return offer;
	}

	@Override
	public void saveOffer(OfferDTO offerDTO) {
		Offer offer = convertOfferDTOToOffer(offerDTO);
		iOfferRepository.save(offer);
	}

	@Override
	public List<Offer> getAllMyOffers(String userEmail) {
		return iOfferRepository.findAll().stream().filter(offer -> offer.getOfferSupplier().getEmail().equals(userEmail)).collect(Collectors.toList());
	}

	@Override
	public Offer getById(long id) {
		return iOfferRepository.findById(id).orElse(null); 
	}
	@Override
	public boolean checkPossibilitiesOfChangeOffer(long offerId) {
		Offer offer= iOfferRepository.findById(offerId).orElse(null);
		return offer.getOrderForOffer().getOrderEndDate().isAfter(LocalDateTime.now());
	}
	
	
}

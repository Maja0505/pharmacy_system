package com.isa.pharmacies_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.StatusOfVacationRequest;
import com.isa.pharmacies_system.domain.storage.PharmacyStorage;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IConfirmationTokenRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageRepository;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageService;

@Service
public class PharmacyStorageService implements IPharmacyStorageService {
	
	private IPharmacyStorageRepository iPharmacyStorageRepository;
	
	@Autowired
	public PharmacyStorageService(IPharmacyStorageRepository iPharmacyStorageRepository) {
		this.iPharmacyStorageRepository = iPharmacyStorageRepository;
	}
	
	//funkcija koja dobavlja skladiste za apoteku
	public PharmacyStorage findPharmacyStorageByPharmacy(Pharmacy pharmacy) {
		return iPharmacyStorageRepository.findAll()
				.stream()
				.filter(pharmacyStorage->pharmacyStorage.getPharmacy().getId() == pharmacy.getId()).findFirst()
				.orElse(null);
	}
	
	@Override
	//funkcija koja projerava da li lijek postoji u magacinu za tu apoteku 
	public boolean checkMedicineHaveInPharmacy(Pharmacy pharmacy, Medicine medicine) {
		PharmacyStorage  pharmacyStorage = findPharmacyStorageByPharmacy(pharmacy);
		return (pharmacyStorage!=null && 
				pharmacyStorage.getPharmacyStorageItems()
				.stream()
				.filter(pharmacyStorageItem -> (pharmacyStorageItem.getMedicineItem().getMedicineName().equals(medicine.getMedicineName()) && 
						pharmacyStorageItem.getMedicineAmount()>0))
				.findFirst()
				.orElse(null)!=null)? true: false;
	}

}

package com.isa.pharmacies_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.pharmacy.SystemLoyalty;
import com.isa.pharmacies_system.domain.user.CategoryOfPatient;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.ISystemLoyaltyRepository;
import com.isa.pharmacies_system.service.iService.ISystemLoyaltyService;

@Service
public class SystemLoyaltyService implements ISystemLoyaltyService {
	
	private ISystemLoyaltyRepository iSystemLoyaltyRepository;
	
	private IPatientRepository iPatientRepository;
	
	@Autowired
	public SystemLoyaltyService(ISystemLoyaltyRepository iSystemLoyaltyRepository, IPatientRepository iPatientRepository) {
		// TODO Auto-generated constructor stub
		this.iSystemLoyaltyRepository = iSystemLoyaltyRepository;
		this.iPatientRepository = iPatientRepository;
	}
	
	@Override
	public SystemLoyalty save(SystemLoyalty systemLoyalty) {
		SystemLoyalty systemLoyaltyReturnVal = iSystemLoyaltyRepository.save(systemLoyalty);
		for (Patient patient :iPatientRepository.findAll()) {
			if(patient.getPatientPoints()>=systemLoyalty.getDiscountRegular() && patient.getPatientPoints()<systemLoyalty.getDiscountSilver()) {
				patient.setCategoryOfPatient(CategoryOfPatient.Regular);
			} else if(patient.getPatientPoints()>=systemLoyalty.getDiscountSilver() && patient.getPatientPoints()<systemLoyalty.getDiscountGold()) {
				patient.setCategoryOfPatient(CategoryOfPatient.Silver);
			} else {
				patient.setCategoryOfPatient(CategoryOfPatient.Gold);
			}
			iPatientRepository.save(patient); 
		}
		return systemLoyaltyReturnVal;
	}

	@Override
	public SystemLoyalty get() {
		// TODO Auto-generated method stub
		return iSystemLoyaltyRepository.findAll().stream().findFirst().orElse(null);
	}

}

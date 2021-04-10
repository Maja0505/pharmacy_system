package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.dto.PharmacyNewDTO;

public interface IPharmacyService {
	
	Pharmacy create(PharmacyNewDTO pharmacyNewDTO) throws Exception;
	List<Pharmacy> getAll();
	Pharmacy getById(Long id) throws Exception;
}

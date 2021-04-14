package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public interface IPharmacyService {
	
	Pharmacy create(PharmacyNewDTO pharmacyNewDTO) throws Exception;
	List<Pharmacy> getAll();
	Pharmacy getById(Long id) throws Exception;
	List<Pharmacy> getAllPharmacyWithFreePharmacistByDate(PharmacistAppointmentTimeDTO timeDTO);

}

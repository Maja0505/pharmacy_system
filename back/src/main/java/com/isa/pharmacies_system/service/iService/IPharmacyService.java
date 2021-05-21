package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import org.springframework.data.domain.Page;

public interface IPharmacyService {
	
	Pharmacy create(PharmacyNewDTO pharmacyNewDTO) throws Exception;
	Page<Pharmacy> getAllWithPages(int page);
	Pharmacy getById(Long id) throws Exception;
	List<Pharmacy> getAllPharmacyWithFreePharmacistByDate(PharmacistAppointmentTimeDTO timeDTO);
	List<PharmacyDTO> sortByPharmacyName(List<PharmacyDTO> pharmacies, Boolean asc);
	List<PharmacyDTO> sortByPharmacyRating(List<PharmacyDTO> pharmacies, Boolean asc);
	List<Pharmacy> getAll();
	List<Pharmacy> getAllPharmacyByDermatologistId(Long dermatologistId);

}

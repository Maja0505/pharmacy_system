package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.PharmacyAdminNewDTO;
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;

public interface IPharmacyAdminService {
	PharmacyAdmin create(PharmacyAdminNewDTO pharmacyAdminNewDTO) throws Exception;
	List<PharmacyAdmin> getAll();
	PharmacyAdmin getById(Long id) throws Exception;
}

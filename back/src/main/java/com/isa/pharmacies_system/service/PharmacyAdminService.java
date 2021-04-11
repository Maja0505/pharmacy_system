package com.isa.pharmacies_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.PharmacyAdminNewDTO;
import com.isa.pharmacies_system.converter.PharmacyAdminConverter;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IPharmacyAdminRepository;
import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.IPharmacyAdminService;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {
	
	private IPharmacyAdminRepository iPharmacyAdminRepository; 
	private IUserRepository iUserRepository; 
	private PharmacyAdminConverter pharmacyAdminConverter;
	private IPharmacyService iPharmacyService;
	
	@Autowired
	public PharmacyAdminService(IPharmacyAdminRepository iPharmacyAdminRepository, IUserRepository iUserRepository, IPharmacyService iPharmacyService) {
		this.iPharmacyAdminRepository = iPharmacyAdminRepository;
		this.iUserRepository= iUserRepository;
		this.iPharmacyService = iPharmacyService;
		this.pharmacyAdminConverter = new PharmacyAdminConverter();
	}
	
	@Override
	public PharmacyAdmin create(PharmacyAdminNewDTO pharmacyAdminNewDTO) throws Exception {
		PharmacyAdmin pharmacyAdmin = pharmacyAdminConverter.convertPharmacyAdminNewDTOToPharmacyAdmin(pharmacyAdminNewDTO);
		pharmacyAdmin.setPharmacyForPharmacyAdmin(iPharmacyService.getById(pharmacyAdminNewDTO.getIdPharmacy()));
		
		iUserRepository.save((Users) pharmacyAdmin);
		return iPharmacyAdminRepository.save(pharmacyAdmin);
	}

	@Override
	public List<PharmacyAdmin> getAll() {
		return iPharmacyAdminRepository.findAll();
	}

	@Override
	public PharmacyAdmin getById(Long id) throws Exception {
		PharmacyAdmin pharmacyAdmin = iPharmacyAdminRepository.findById(id).orElse(null);
		if (pharmacyAdmin==null) {
			throw new Exception("Don't exist pharmacy with id "+id+"!");
		}
		return pharmacyAdmin;
	}


}

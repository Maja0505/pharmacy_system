package com.isa.pharmacies_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.SystemAdminNewDTO;
import com.isa.pharmacies_system.converter.SystemAdminConverter;
import com.isa.pharmacies_system.domain.user.SystemAdmin;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.ISystemAdminRepository;
import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.ISystemAdminService;

@Service
public class SystemAdminService implements ISystemAdminService {
	private IUserRepository iUserRepository;
	private ISystemAdminRepository iSystemAdminRepository;
	private SystemAdminConverter systemAdminConverter;
	
	public SystemAdminService(ISystemAdminRepository iSystemAdminRepository,IUserRepository iUserRepository) {
		this.iSystemAdminRepository = iSystemAdminRepository;
		this.iUserRepository = iUserRepository;
		this.systemAdminConverter = new SystemAdminConverter();
	}
	
	@Override
	public SystemAdmin create(SystemAdminNewDTO systemAdminNewDTO) {
		SystemAdmin systemAdmin = systemAdminConverter.convertSystemAdminNewDTOToSystemAdmin(systemAdminNewDTO);
		iUserRepository.save((Users)systemAdmin);
		return iSystemAdminRepository.save(systemAdmin);
	}

	@Override
	public SystemAdmin getById(Long id) throws Exception {
		SystemAdmin systemAdmin = iSystemAdminRepository.findById(id).orElse(null);
		if (systemAdmin==null) {
			throw new Exception("Don't exist system admin with id "+id + "!!!");
		}
		return systemAdmin;
	}

	@Override
	public List<SystemAdmin> getAll() {
		return iSystemAdminRepository.findAll();
	}

}

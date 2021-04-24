package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.SystemAdminNewDTO;
import com.isa.pharmacies_system.domain.user.SystemAdmin;

public interface ISystemAdminService {
	SystemAdmin create(SystemAdminNewDTO systemAdminNewDTO);
	SystemAdmin getById(Long id) throws Exception;
	List<SystemAdmin> getAll();
}

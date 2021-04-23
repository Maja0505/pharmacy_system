package com.isa.pharmacies_system.converter;

import java.util.HashSet;

import com.isa.pharmacies_system.DTO.SystemAdminNewDTO;
import com.isa.pharmacies_system.domain.complaint.ComplaintResponse;
import com.isa.pharmacies_system.domain.user.SystemAdmin;
import com.isa.pharmacies_system.domain.user.TypeOfUser;

public class SystemAdminConverter {
	public SystemAdminConverter() {}
	
	public SystemAdmin convertSystemAdminNewDTOToSystemAdmin(SystemAdminNewDTO systemAdminNewDTO) {
		SystemAdmin systemAdmin = new SystemAdmin();
		systemAdmin.setEmail(systemAdminNewDTO.getEmail());
		systemAdmin.setFirstName(systemAdminNewDTO.getFirstName());
		systemAdmin.setLastName(systemAdminNewDTO.getLastName());
		systemAdmin.setPassword(systemAdminNewDTO.getPassword());
		systemAdmin.setPhoneNumber(systemAdminNewDTO.getPhoneNumber());
		systemAdmin.setSystemAdminComplaintResponses(new HashSet<ComplaintResponse>());
		systemAdmin.setTypeOfUser(TypeOfUser.System_admin);
		systemAdmin.setUserAddress(systemAdminNewDTO.getResidentialAddress());
		return systemAdmin;
	}
	
	public SystemAdminNewDTO convertSystemAdminToSystemAdminNewDTO(SystemAdmin systemAdmin) {
		SystemAdminNewDTO systemAdminNewDTO = new SystemAdminNewDTO();
		systemAdminNewDTO.setEmail(systemAdmin.getEmail());
		systemAdminNewDTO.setFirstName(systemAdmin.getFirstName());
		systemAdminNewDTO.setLastName(systemAdmin.getLastName());
		systemAdminNewDTO.setPassword(systemAdmin.getPassword());
		systemAdminNewDTO.setPhoneNumber(systemAdmin.getPhoneNumber());
		systemAdminNewDTO.setResidentialAddress(systemAdmin.getUserAddress());
		return systemAdminNewDTO;
	}
}

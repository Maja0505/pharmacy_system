package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class SystemAdminNewDTO extends UserNewDTO{
	public SystemAdminNewDTO() {
		super();
	}

	public SystemAdminNewDTO(String email, String firstName, String lastName, Address residentialAddress,
			String phoneNumber, String password) {
		super(email, firstName, lastName, residentialAddress, phoneNumber, password);
		// TODO Auto-generated constructor stub
	}
	
}

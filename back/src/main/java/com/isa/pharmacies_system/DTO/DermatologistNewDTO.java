package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class DermatologistNewDTO extends UserNewDTO {

	public DermatologistNewDTO() {
		super();
	}

	public DermatologistNewDTO(String email, String firstName, String lastName, Address residentialAddress,
			String phoneNumber, String password) {
		super(email, firstName, lastName, residentialAddress, phoneNumber, password);
	}
	
	
}

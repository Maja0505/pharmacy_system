package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class SupplierNewDTO extends UserNewDTO {
	public SupplierNewDTO() {
		super();
	}

	public SupplierNewDTO(String email, String firstName, String lastName, Address residentialAddress,
			String phoneNumber, String password) {
		super(email, firstName, lastName, residentialAddress, phoneNumber, password);
	}
}

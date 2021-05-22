package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.DTO.UserNewDTO;

public class PatientNewDTO extends UserNewDTO{
	private String confirmPassword;
	
	public PatientNewDTO() {
		super();
	}

	public PatientNewDTO(String email, String firstName, String lastName, Address residentialAddress,
			String phoneNumber, String password, String confirmPassword) {
		super(email, firstName, lastName, residentialAddress, phoneNumber, password);
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}

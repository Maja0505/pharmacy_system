package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class PharmacyAdminNewDTO extends UserNewDTO {

	private Long idPharmacy;

	public PharmacyAdminNewDTO() {
		super();
	}

	public PharmacyAdminNewDTO(String email, String firstName, String lastName, Address residentialAddress,
			String phoneNumber, String password, Long idPharmacy) {
		super(email, firstName, lastName, residentialAddress, phoneNumber, password);
		// TODO Auto-generated constructor stub
		this.idPharmacy = idPharmacy;
	}

	public Long getIdPharmacy() {
		return idPharmacy;
	}

	public void setIdPharmacy(Long idPharmacy) {
		this.idPharmacy = idPharmacy;
	}

}

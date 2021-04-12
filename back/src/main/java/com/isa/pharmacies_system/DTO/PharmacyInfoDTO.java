package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public abstract class PharmacyInfoDTO {
	
	private String pharmacyName;
	private Address pharmacyAddress;

	public PharmacyInfoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PharmacyInfoDTO(String pharmacyName, Address pharmacyAddress) {
		super();
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Address getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(Address pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

}

package com.isa.pharmacies_system.DTO;

import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyForMedicineDTO {
	private String pharmacyName;
	
	private double priceOfMedcine;
	
	public PharmacyForMedicineDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PharmacyForMedicineDTO(String pharmacyName, double priceOfMedcine) {
		super();
		this.pharmacyName = pharmacyName;
		this.priceOfMedcine = priceOfMedcine;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public double getPriceOfMedcine() {
		return priceOfMedcine;
	}

	public void setPriceOfMedcine(double priceOfMedcine) {
		this.priceOfMedcine = priceOfMedcine;
	}
	
}

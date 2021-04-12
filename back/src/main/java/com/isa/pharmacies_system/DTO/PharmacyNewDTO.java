package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public class PharmacyNewDTO extends PharmacyInfoDTO {

	private String pharmacyDescription;
	private double priceForPharmacistAppointment;
	private double priceForDermatologistAppointment;

	public PharmacyNewDTO() {
		super();
	}

	public PharmacyNewDTO(String pharmacyName, Address pharmacyAddress, String pharmacyDescription,
			double priceForPharmacistAppointment, double priceForDermatologistAppointment) {
		super(pharmacyName, pharmacyAddress);
		// TODO Auto-generated constructor stub
		this.pharmacyDescription = pharmacyDescription;
		this.priceForPharmacistAppointment = priceForPharmacistAppointment;
		this.priceForDermatologistAppointment = priceForDermatologistAppointment;
	}

	public String getPharmacyDescription() {
		return pharmacyDescription;
	}

	public void setPharmacyDescription(String pharmacyDescription) {
		this.pharmacyDescription = pharmacyDescription;
	}

	public double getPriceForPharmacistAppointment() {
		return priceForPharmacistAppointment;
	}

	public void setPriceForPharmacistAppointment(double priceForPharmacistAppointment) {
		this.priceForPharmacistAppointment = priceForPharmacistAppointment;
	}

	public double getPriceForDermatologistAppointment() {
		return priceForDermatologistAppointment;
	}

	public void setPriceForDermatologistAppointment(double priceForDermatologistAppointment) {
		this.priceForDermatologistAppointment = priceForDermatologistAppointment;
	}

}

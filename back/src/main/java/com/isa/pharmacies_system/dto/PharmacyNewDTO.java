package com.isa.pharmacies_system.dto;

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public class PharmacyNewDTO {
	
	private String pharmacyName;
	private String streetName;
	private String streetNumber;
	private String city;
	private String country;
	private String pharmacyDescription;
	private double priceForPharmacistAppointment;
	private double priceForDermatologistAppointment;
	
	public PharmacyNewDTO() {
	}
	
	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

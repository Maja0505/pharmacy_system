package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class PharmacyForEPrescriptionDTO {
	private long pharmacyId;
	private String pharmacyName;
	private Address pharmacyAddress;
	private double pharmacyRating;
	private double totalPrice;
	
	public PharmacyForEPrescriptionDTO() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyForEPrescriptionDTO(long pharmacyId, String pharmacyName, Address pharmacyAddress,
			double pharmacyRating, double totalPrice) {
		super();
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
		this.pharmacyRating = pharmacyRating;
		this.totalPrice = totalPrice;
	}

	public long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
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

	public double getPharmacyRating() {
		return pharmacyRating;
	}

	public void setPharmacyRating(double pharmacyRating) {
		this.pharmacyRating = pharmacyRating;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}

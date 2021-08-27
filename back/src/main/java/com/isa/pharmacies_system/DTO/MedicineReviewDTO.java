package com.isa.pharmacies_system.DTO;

import java.util.ArrayList;
import java.util.List;

import com.isa.pharmacies_system.domain.medicine.TypeOfMedicine;

public class MedicineReviewDTO {
	private String medicneName;

	private TypeOfMedicine typeOfMedicine;

	private double ratingOfMedicine;

	private List<PharmacyForMedicineDTO> pharmacyWithMedicine;

	public MedicineReviewDTO() {
		// TODO Auto-generated constructor stub
		this.pharmacyWithMedicine = new ArrayList<PharmacyForMedicineDTO>();
	}

	public MedicineReviewDTO(String medicneName, TypeOfMedicine typeOfMedicine, double ratingOfMedicine) {
		super();
		this.medicneName = medicneName;
		this.typeOfMedicine = typeOfMedicine;
		this.ratingOfMedicine = ratingOfMedicine;
		this.pharmacyWithMedicine = new ArrayList<PharmacyForMedicineDTO>();
	}

	public String getMedicneName() {
		return medicneName;
	}

	public void setMedicneName(String medicneName) {
		this.medicneName = medicneName;
	}

	public TypeOfMedicine getTypeOfMedicine() {
		return typeOfMedicine;
	}

	public void setTypeOfMedicine(TypeOfMedicine typeOfMedicine) {
		this.typeOfMedicine = typeOfMedicine;
	}

	public double getRatingOfMedicine() {
		return ratingOfMedicine;
	}

	public void setRatingOfMedicine(double ratingOfMedicine) {
		this.ratingOfMedicine = ratingOfMedicine;
	}

	public List<PharmacyForMedicineDTO> getPharmacyWithMedicine() {
		return pharmacyWithMedicine;
	}

	public void setPharmacyWithMedicine(List<PharmacyForMedicineDTO> pharmacyWithMedicine) {
		this.pharmacyWithMedicine = pharmacyWithMedicine;
	}

}

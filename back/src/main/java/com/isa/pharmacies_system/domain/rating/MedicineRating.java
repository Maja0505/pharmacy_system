package com.isa.pharmacies_system.domain.rating;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="medicine_ratings")
public class MedicineRating extends Rating {
	
	//apoteka
	//pacijent samo jednom moze da da ocenu apoteci, posle ide update
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicineForRating;

	public MedicineRating() {
		
	}

	public MedicineRating(Medicine medicineForRating) {
		this.medicineForRating = medicineForRating;
	}

	public MedicineRating(long id, RatingScale grade, Patient patientWithRating, TypeOfRating typeOfRating, Medicine medicineForRating) {
		super(id, grade, patientWithRating, typeOfRating);
		this.medicineForRating = medicineForRating;
	}

	public Medicine getMedicineForRating() {
		return medicineForRating;
	}

	public void setMedicineForRating(Medicine medicineForRating) {this.medicineForRating = medicineForRating;}
	
}

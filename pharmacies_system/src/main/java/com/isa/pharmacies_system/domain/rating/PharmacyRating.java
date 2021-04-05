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

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

@Entity
@Table(name="pharmacy_ratings")
public class PharmacyRating extends Rating {
	
	//apoteka
	//pacijent samo jednom moze da da ocenu apoteci, posle ide update
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacyForRating;

	public PharmacyRating() {
		
	}

	public PharmacyRating(Pharmacy pharmacyForRating) {
		super();
		this.pharmacyForRating = pharmacyForRating;
	}

	public Pharmacy getPharmacyForRating() {
		return pharmacyForRating;
	}

	public void setPharmacyForRating(Pharmacy pharmacyForRating) {
		this.pharmacyForRating = pharmacyForRating;
	}
	
}

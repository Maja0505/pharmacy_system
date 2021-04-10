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
import com.isa.pharmacies_system.domain.user.Pharmacist;

@Entity
@Table(name="pharmacist_ratings")
public class PharmacistRating extends Rating{

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Pharmacist pharmacistForRating;

	public PharmacistRating() {
		
	}

	public PharmacistRating(Pharmacist pharmacistForRating) {
		super();
		this.pharmacistForRating = pharmacistForRating;
	}

	public Pharmacist getPharmacistForRating() {
		return pharmacistForRating;
	}

	public void setPharmacistForRating(Pharmacist pharmacistForRating) {
		this.pharmacistForRating = pharmacistForRating;
	}
	
}

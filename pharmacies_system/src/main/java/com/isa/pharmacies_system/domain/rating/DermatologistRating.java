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
import com.isa.pharmacies_system.domain.user.Dermatologist;

@Entity
@Table(name="dermatologist_ratings")
public class DermatologistRating extends Rating{
	
	//dermatolog
	//pacijent samo jednom moze da da ocenu apoteci, posle ide update
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Dermatologist dermatologistForRating;

	public DermatologistRating() {
		
	}

	public DermatologistRating(Dermatologist dermatologistForRating) {
		super();
		this.dermatologistForRating = dermatologistForRating;
	}

	public Dermatologist getDermatologistForRating() {
		return dermatologistForRating;
	}

	public void setDermatologistForRating(Dermatologist dermatologistForRating) {
		this.dermatologistForRating = dermatologistForRating;
	}
	
}

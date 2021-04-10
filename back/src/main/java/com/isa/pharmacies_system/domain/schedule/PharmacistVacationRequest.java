package com.isa.pharmacies_system.domain.schedule;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.user.Pharmacist;

@Entity
@Table(name="dermatologist_vacation_requests")
public class PharmacistVacationRequest extends VacationRequest{

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacist vacationRequestPharmacist;
	
	public PharmacistVacationRequest() {
		// TODO Auto-generated constructor stub
	}

	public PharmacistVacationRequest(Pharmacist vacationRequestPharmacist) {
		super();
		this.vacationRequestPharmacist = vacationRequestPharmacist;
	}

	public Pharmacist getVacationRequestPharmacist() {
		return vacationRequestPharmacist;
	}

	public void setVacationRequestPharmacist(Pharmacist vacationRequestPharmacist) {
		this.vacationRequestPharmacist = vacationRequestPharmacist;
	}
	
}

package com.isa.pharmacies_system.domain.schedule;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.pharmacies_system.domain.user.Dermatologist;

@Entity
@Table(name="dermatologist_vacation_requests")
public class DermatologistVacationRequest extends VacationRequest {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist vacationRequestDermatologist;
	
	public DermatologistVacationRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public DermatologistVacationRequest(Dermatologist vacationRequestDermatologist) {
		super();
		this.vacationRequestDermatologist = vacationRequestDermatologist;
	}

	public Dermatologist getVacationRequestDermatologist() {
		return vacationRequestDermatologist;
	}

	public void setVacationRequestDermatologist(Dermatologist vacationRequestDermatologist) {
		this.vacationRequestDermatologist = vacationRequestDermatologist;
	}
	
	
}

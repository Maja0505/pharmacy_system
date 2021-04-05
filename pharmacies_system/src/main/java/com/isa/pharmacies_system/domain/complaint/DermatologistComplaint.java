package com.isa.pharmacies_system.domain.complaint;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.pharmacies_system.domain.user.Dermatologist;

@Entity
@Table(name="dermatologist_complaints")
public class DermatologistComplaint extends Complaint {
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist dermatologistForComplaint;
	
	public DermatologistComplaint() {
		// TODO Auto-generated constructor stub
	}

	public DermatologistComplaint(Dermatologist dermatologistForComplaint) {
		super();
		this.dermatologistForComplaint = dermatologistForComplaint;
	}

	public Dermatologist getDermatologistForComplaint() {
		return dermatologistForComplaint;
	}

	public void setDermatologistForComplaint(Dermatologist dermatologistForComplaint) {
		this.dermatologistForComplaint = dermatologistForComplaint;
	}
	
	
}

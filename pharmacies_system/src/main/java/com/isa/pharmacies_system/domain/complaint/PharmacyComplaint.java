package com.isa.pharmacies_system.domain.complaint;

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
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

@Entity
@Table(name="pharmacy_complaints")
public class PharmacyComplaint extends Complaint{
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacyForComplaint;
	
	public PharmacyComplaint() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyComplaint(Pharmacy pharmacyForComplaint) {
		super();
		this.pharmacyForComplaint = pharmacyForComplaint;
	}

	public Pharmacy getPharmacyForComplaint() {
		return pharmacyForComplaint;
	}

	public void setPharmacyForComplaint(Pharmacy pharmacyForComplaint) {
		this.pharmacyForComplaint = pharmacyForComplaint;
	}
	
}

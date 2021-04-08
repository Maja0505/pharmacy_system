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
import com.isa.pharmacies_system.domain.user.Pharmacist;

@Entity
@Table(name="pharmacist_complaints")
public class PharmacistComplaint extends Complaint {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacist pharmacistForComplaint;
	
	public PharmacistComplaint() {
		// TODO Auto-generated constructor stub
	}

	public PharmacistComplaint(Pharmacist pharmacistForComplaint) {
		super();
		this.pharmacistForComplaint = pharmacistForComplaint;
	}

	public Pharmacist getPharmacistForComplaint() {
		return pharmacistForComplaint;
	}

	public void setPharmacistForComplaint(Pharmacist pharmacistForComplaint) {
		this.pharmacistForComplaint = pharmacistForComplaint;
	}
	
}

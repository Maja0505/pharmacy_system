package com.isa.pharmacies_system.domain.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.*;
import com.isa.pharmacies_system.domain.complaint.PharmacistComplaint;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.rating.PharmacistRating;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;

import javax.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name="pharmacists")
public class Pharmacist extends Users {

	//@OneToOne(cascade = CascadeType.ALL)
	//private WorkerSchedule pharmacistSchedule;

	@JsonManagedReference
	@OneToMany(mappedBy = "vacationRequestPharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("vacationRequestPharmacist")
	private Set<PharmacistVacationRequest> pharmacistVacationRequests = new HashSet<PharmacistVacationRequest>();
	
	@Column(name="pharmacistAverageRating", unique=false, nullable=false)
	private double pharmacistAverageRating;

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacistForAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacistAppointment> pharmacistAppointments = new HashSet<PharmacistAppointment>();

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacy pharmacyForPharmacist;

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacistForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacistComplaint> pharmacistComplaints = new HashSet<PharmacistComplaint>();

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacistForRating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacistRating> pharmacistRatings = new HashSet<PharmacistRating>();
	
	public Pharmacist() {
		
	}
	
	

	public Pharmacist(WorkerSchedule pharmacistSchedule, Set<PharmacistVacationRequest> pharmacistVacationRequests,
			double pharmacistAverageRating, Set<PharmacistAppointment> pharmacistAppointments,
			Pharmacy pharmacyForPharmacist, Set<PharmacistComplaint> pharmacistComplaints,
			Set<PharmacistRating> pharmacistRatings) {
		super();
		//this.pharmacistSchedule = pharmacistSchedule;
		this.pharmacistVacationRequests = pharmacistVacationRequests;
		this.pharmacistAverageRating = pharmacistAverageRating;
		this.pharmacistAppointments = pharmacistAppointments;
		this.pharmacyForPharmacist = pharmacyForPharmacist;
		this.pharmacistComplaints = pharmacistComplaints;
		this.pharmacistRatings = pharmacistRatings;
	}



	/*public WorkerSchedule getPharmacistSchedule() {
		return pharmacistSchedule;
	}

	public void setPharmacistSchedule(WorkerSchedule pharmacistSchedule) {
		this.pharmacistSchedule = pharmacistSchedule;
	}*/

	public Set<PharmacistVacationRequest> getPharmacistVacationRequests() {
		return pharmacistVacationRequests;
	}

	public void setPharmacistVacationRequests(Set<PharmacistVacationRequest> pharmacistVacationRequests) {
		this.pharmacistVacationRequests = pharmacistVacationRequests;
	}

	public double getPharmacistAverageRating() {
		return pharmacistAverageRating;
	}

	public void setPharmacistAverageRating(double pharmacistAverageRating) {
		this.pharmacistAverageRating = pharmacistAverageRating;
	}

	public Set<PharmacistAppointment> getPharmacistAppointments() {
		return pharmacistAppointments;
	}

	public void setPharmacistAppointments(Set<PharmacistAppointment> pharmacistAppointments) {
		this.pharmacistAppointments = pharmacistAppointments;
	}

	public Pharmacy getPharmacyForPharmacist() {
		return pharmacyForPharmacist;
	}

	public void setPharmacyForPharmacist(Pharmacy pharmacyForPharmacist) {
		this.pharmacyForPharmacist = pharmacyForPharmacist;
	}

	public Set<PharmacistComplaint> getPharmacistComplaints() {
		return pharmacistComplaints;
	}

	public void setPharmacistComplaints(Set<PharmacistComplaint> pharmacistComplaints) {
		this.pharmacistComplaints = pharmacistComplaints;
	}

	public Set<PharmacistRating> getPharmacistRatings() {
		return pharmacistRatings;
	}

	public void setPharmacistRatings(Set<PharmacistRating> pharmacistRatings) {
		this.pharmacistRatings = pharmacistRatings;
	}
	
}

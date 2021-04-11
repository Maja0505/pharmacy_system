package com.isa.pharmacies_system.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.complaint.Complaint;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.rating.Rating;
import com.isa.pharmacies_system.domain.schedule.Appointment;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;

@Entity
@Table(name = "patient")
public class Patient extends Users {
	

	@Column(name = "patientPoints", unique = false, nullable = false)
	private double patientPoints;
	
	@Enumerated(EnumType.ORDINAL)
	private CategoryOfPatient categoryOfPatient;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Medicine> medicineAllergies = new HashSet<Medicine>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientWithPharmacistAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacistAppointment> pharmacistAppointments = new HashSet<PharmacistAppointment>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientWithDermatologistAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermatologistAppointment> dermatologistAppointment = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Pharmacy> pharmaciesSubscription = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientForMedicineReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicineReservation> patientMedicineReservations = new HashSet<MedicineReservation>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientForEPrescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EPrescription> patientEPrescriptions = new HashSet<EPrescription>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientForRecipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recipe> patientRecipe = new HashSet<Recipe>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientWithComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint> patientComplaints = new HashSet<Complaint>();

	@JsonManagedReference
	@OneToMany(mappedBy = "patientWithRating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rating> patientRatings = new HashSet<Rating>();
	
	public Patient() {}

	public double getPatientPoints() {
		return patientPoints;
	}

	public void setPatientPoints(double patientPoints) {
		this.patientPoints = patientPoints;
	}

	public CategoryOfPatient getCategoryOfPatient() {
		return categoryOfPatient;
	}

	public void setCategoryOfPatient(CategoryOfPatient categoryOfPatient) {
		this.categoryOfPatient = categoryOfPatient;
	}

	public Set<Medicine> getMedicineAllergies() {
		return medicineAllergies;
	}

	public void setMedicineAllergies(Set<Medicine> medicineAllergies) {
		this.medicineAllergies = medicineAllergies;
	}

	public Set<PharmacistAppointment> getPharmacistAppointments() {
		return pharmacistAppointments;
	}

	public void setPharmacistAppointments(Set<PharmacistAppointment> pharmacistAppointments) {
		this.pharmacistAppointments = pharmacistAppointments;
	}

	public Set<DermatologistAppointment> getDermatologistAppointment() {
		return dermatologistAppointment;
	}

	public void setDermatologistAppointment(Set<DermatologistAppointment> dermatologistAppointment) {
		this.dermatologistAppointment = dermatologistAppointment;
	}

	public Set<Pharmacy> getPharmaciesSubscription() {
		return pharmaciesSubscription;
	}

	public void setPharmaciesSubscription(Set<Pharmacy> pharmaciesSubscription) {
		this.pharmaciesSubscription = pharmaciesSubscription;
	}

	public Set<MedicineReservation> getPatientMedicineReservations() {
		return patientMedicineReservations;
	}

	public void setPatientMedicineReservations(Set<MedicineReservation> patientMedicineReservations) {
		this.patientMedicineReservations = patientMedicineReservations;
	}

	public Set<EPrescription> getPatientEPrescriptions() {
		return patientEPrescriptions;
	}

	public void setPatientEPrescriptions(Set<EPrescription> patientEPrescriptions) {
		this.patientEPrescriptions = patientEPrescriptions;
	}

	public Set<Recipe> getPatientRecipe() {
		return patientRecipe;
	}

	public void setPatientRecipe(Set<Recipe> patientRecipe) {
		this.patientRecipe = patientRecipe;
	}

	public Set<Complaint> getPatientComplaints() {
		return patientComplaints;
	}

	public void setPatientComplaints(Set<Complaint> patientComplaints) {
		this.patientComplaints = patientComplaints;
	}

	public Set<Rating> getPatientRatings() {
		return patientRatings;
	}

	public void setPatientRatings(Set<Rating> patientRatings) {
		this.patientRatings = patientRatings;
	}
}

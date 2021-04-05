package com.isa.pharmacies_system.domain.schedule;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="dermatologist_appointment")
public class DermatologistAppointment extends Appointment {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime dermatologistAppointmentStartTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime dermatologistAppointmentEndTime;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist dermatologistForAppointment;

	@JsonIgnoreProperties({"workerSchedule","pharmacyComplaints","pharmacyAverageRating","pharmacyDescription","pharmacyMedicineReservations","pharmacyName","pharmacyAddress","dermatologistsInPharmacy","pharmacistsInPharmacy","dermatologistAppointmentsInPharmacy","promotionsForPharmacy","pharmacyRatings","pharmacistForAppointment"})
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacyForDermatologistAppointment;

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Patient patientWithDermatologistAppointment;


	public DermatologistAppointment() {
		
	}
	
	public DermatologistAppointment(LocalDateTime dermatologistAppointmentStartTime, LocalDateTime dermatologistAppointmentEndTime,
			Dermatologist dermatologistForAppointment, Pharmacy pharmacyForDermatologistAppointment) {
		super();
		this.dermatologistAppointmentStartTime = dermatologistAppointmentStartTime;
		this.dermatologistAppointmentEndTime = dermatologistAppointmentEndTime;
		this.dermatologistForAppointment = dermatologistForAppointment;
		this.pharmacyForDermatologistAppointment = pharmacyForDermatologistAppointment;
	}



	public LocalDateTime getDermatologistAppointmentStartTime() {
		return dermatologistAppointmentStartTime;
	}

	public void setDermatologistAppointmentStartTime(LocalDateTime dermatologistAppointmentStartTime) {
		this.dermatologistAppointmentStartTime = dermatologistAppointmentStartTime;
	}

	public LocalDateTime getDermatologistAppointmentEndTime() {
		return dermatologistAppointmentEndTime;
	}

	public void setDermatologistAppointmentEndTime(LocalDateTime dermatologistAppointmentEndTime) {
		this.dermatologistAppointmentEndTime = dermatologistAppointmentEndTime;
	}

	public Dermatologist getDermatologistForAppointment() {
		return dermatologistForAppointment;
	}

	public void setDermatologistForAppointment(Dermatologist dermatologistForAppointment) {
		this.dermatologistForAppointment = dermatologistForAppointment;
	}

	public Pharmacy getPharmacyForDermatologistAppointment() {
		return pharmacyForDermatologistAppointment;
	}

	public void setPharmacyForDermatologistAppointment(Pharmacy pharmacyForDermatologistAppointment) {
		this.pharmacyForDermatologistAppointment = pharmacyForDermatologistAppointment;
	}
	
}

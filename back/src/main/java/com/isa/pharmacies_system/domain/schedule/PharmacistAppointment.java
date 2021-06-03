package com.isa.pharmacies_system.domain.schedule;

import java.time.Instant;
import java.time.LocalDateTime;

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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;

@Entity
@Table(name="pharmacist_appointment")
public class PharmacistAppointment extends Appointment {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime pharmacistAppointmentStartTime;

	@Column(name="pharmacistAppointmentDuration", unique=false, nullable=false)
	private long pharmacistAppointmentDuration;

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Patient patientWithPharmacistAppointment;

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Pharmacist pharmacistForAppointment;
	
	public PharmacistAppointment() {
		
	}

	public LocalDateTime getPharmacistAppointmentStartTime() {
		return pharmacistAppointmentStartTime;
	}

	public void setPharmacistAppointmentStartTime(LocalDateTime pharmacistAppointmentStartTime) {
		this.pharmacistAppointmentStartTime = pharmacistAppointmentStartTime;
	}

	public long getPharmacistAppointmentDuration() {
		return pharmacistAppointmentDuration;
	}

	public void setPharmacistAppointmentDuration(long pharmacistAppointmentDuration) {
		this.pharmacistAppointmentDuration = pharmacistAppointmentDuration;
	}

	public Patient getPatientWithPharmacistAppointment() {
		return patientWithPharmacistAppointment;
	}

	public void setPatientWithPharmacistAppointment(Patient patientWithPharmacistAppointment) {
		this.patientWithPharmacistAppointment = patientWithPharmacistAppointment;
	}

	public Pharmacist getPharmacistForAppointment() {
		return pharmacistForAppointment;
	}

	public void setPharmacistForAppointment(Pharmacist pharmacistForAppointment) {
		this.pharmacistForAppointment = pharmacistForAppointment;
	}
}

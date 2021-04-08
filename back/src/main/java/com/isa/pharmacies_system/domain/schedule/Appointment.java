package com.isa.pharmacies_system.domain.schedule;

import static javax.persistence.InheritanceType.JOINED;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="appointments")
@Inheritance(strategy=JOINED)
public class Appointment {
	@Id
	@SequenceGenerator(name = "mySeqGenAppointment", sequenceName = "mySeqAppointment", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenAppointment")
	private long id;
	
	@Column(name="appointmentPrice", unique=false, nullable=false)
	private double appointmentPrice;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfAppointment statusOfAppointment;

	@Column(name="appointmentPoints", unique=false, nullable=false)
	private double appointmentPoints;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfAppointment typeOfAppointment;
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAppointmentPrice() {
		return appointmentPrice;
	}

	public void setAppointmentPrice(double appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}

	public StatusOfAppointment getStatusOfAppointment() {
		return statusOfAppointment;
	}

	public void setStatusOfAppointment(StatusOfAppointment statusOfAppointment) {
		this.statusOfAppointment = statusOfAppointment;
	}

	public double getAppointmentPoints() {
		return appointmentPoints;
	}

	public void setAppointmentPoints(double appointmentPoints) {
		this.appointmentPoints = appointmentPoints;
	}

	public TypeOfAppointment getTypeOfAppointment() {
		return typeOfAppointment;
	}

	public void setTypeOfAppointment(TypeOfAppointment typeOfAppointment) {
		this.typeOfAppointment = typeOfAppointment;
	}
}

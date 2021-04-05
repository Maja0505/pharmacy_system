package com.isa.pharmacies_system.domain.complaint;

import static javax.persistence.InheritanceType.JOINED;

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

import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="complaints")
@Inheritance(strategy=JOINED)
public class Complaint {
	@Id
	@SequenceGenerator(name = "mySeqGenComplaint", sequenceName = "mySeqComplaint", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenComplaint")
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patientWithComplaint;
	
	@Column(name="complaintContent", unique=false, nullable=false)
	private String content;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfComplaint typeOfComplaint;
	
	public Complaint() {
		// TODO Auto-generated constructor stub
	}

	public Complaint(long id, Patient patientWithComplaint, String content, TypeOfComplaint typeOfComplaint) {
		super();
		this.id = id;
		this.patientWithComplaint = patientWithComplaint;
		this.content = content;
		this.typeOfComplaint = typeOfComplaint;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patient getPatientWithComplaint() {
		return patientWithComplaint;
	}

	public void setPatientWithComplaint(Patient patientWithComplaint) {
		this.patientWithComplaint = patientWithComplaint;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TypeOfComplaint getTypeOfComplaint() {
		return typeOfComplaint;
	}

	public void setTypeOfComplaint(TypeOfComplaint typeOfComplaint) {
		this.typeOfComplaint = typeOfComplaint;
	}
	
	
}

package com.isa.pharmacies_system.domain.medicine;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="e_prescriptions")
public class EPrescription {
	@Id
	@SequenceGenerator(name = "mySeqGenEPrescription", sequenceName = "mySeqEPrescription", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenEPrescription")
	private long id;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patientForEPrescription;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Instant creationDate;
	
	//spisak lekova sa kolicinama
	@JsonManagedReference
	@OneToMany(mappedBy = "ePrescriton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EPrescriptionItem> ePrescriptionItems = new HashSet<EPrescriptionItem>();
	
	
	public EPrescription() {
		// TODO Auto-generated constructor stub
	}

	

	public EPrescription(long id, Patient patientForEPrescription, Instant creationDate,
			Set<EPrescriptionItem> ePrescriptionItems) {
		super();
		this.id = id;
		this.patientForEPrescription = patientForEPrescription;
		this.creationDate = creationDate;
		this.ePrescriptionItems = ePrescriptionItems;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patient getPatientForEPrescription() {
		return patientForEPrescription;
	}

	public void setPatientForEPrescription(Patient patientForEPrescription) {
		this.patientForEPrescription = patientForEPrescription;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}



	public Set<EPrescriptionItem> getePrescriptionItems() {
		return ePrescriptionItems;
	}



	public void setePrescriptionItems(Set<EPrescriptionItem> ePrescriptionItems) {
		this.ePrescriptionItems = ePrescriptionItems;
	}

	
	
	
}

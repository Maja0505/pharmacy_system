package com.isa.pharmacies_system.domain.schedule;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Dermatologist;

@Entity
@Table(name="worker_schedule")
public class WorkerSchedule {
	
	
	@Id
	@SequenceGenerator(name = "mySeqGenWorkerSchedule", sequenceName = "mySeqWorkerSchedule", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenWorkerSchedule")
	private long id;


	@JsonIgnoreProperties({"workingStartTime","workingEndTime","statusOfWorkingHours","workerSchedule"})
	@OneToMany(mappedBy = "workerSchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<WorkingHours> workingHours = new HashSet<WorkingHours>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist dermatologist;

	/*
	@OneToOne(mappedBy = "pharmacistSchedule")
	private Pharmacist pharmacists;
	*/
	
	public WorkerSchedule() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<WorkingHours> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Set<WorkingHours> workingHours) {
		this.workingHours = workingHours;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}
}

package com.isa.pharmacies_system.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.complaint.DermatologistComplaint;
import com.isa.pharmacies_system.domain.rating.DermatologistRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;

@Entity
@Table(name="dermatologists")
public class Dermatologist extends Users{

	@JsonManagedReference
	@OneToMany(mappedBy = "dermatologist",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<WorkerSchedule> dermatologistSchedules = new HashSet<WorkerSchedule>();

	@JsonManagedReference
	@OneToMany(mappedBy = "vacationRequestDermatologist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DermatologistVacationRequest> dermatologistVacationRequests = new HashSet<DermatologistVacationRequest>();
	
	@Column(name="dermatologistAverageRating", unique=false, nullable=false)
	private double dermatologistAverageRating;

	@JsonManagedReference
	@OneToMany(mappedBy = "dermatologistForAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermatologistAppointment> dermatologistAppointments = new HashSet<DermatologistAppointment>();

	@JsonManagedReference
	@OneToMany(mappedBy = "dermatologistForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermatologistComplaint> dermatologistComplaints = new HashSet<DermatologistComplaint>();

	@JsonManagedReference
	@OneToMany(mappedBy = "dermatologistForRating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermatologistRating> dermatologistRatings = new HashSet<DermatologistRating>();
	
	public Dermatologist(){
		
	}

	public Set<WorkerSchedule> getDermatologistSchedules() {
		return dermatologistSchedules;
	}

	public void setDermatologistSchedules(Set<WorkerSchedule> dermatologistSchedules) {
		this.dermatologistSchedules = dermatologistSchedules;
	}

	public Set<DermatologistVacationRequest> getDermatologistVacationRequests() {
		return dermatologistVacationRequests;
	}

	public void setDermatologistVacationRequests(Set<DermatologistVacationRequest> dermatologistVacationRequests) {
		this.dermatologistVacationRequests = dermatologistVacationRequests;
	}

	public double getDermatologistAverageRating() {
		return dermatologistAverageRating;
	}

	public void setDermatologistAverageRating(double dermatologistAverageRating) {
		this.dermatologistAverageRating = dermatologistAverageRating;
	}

	public Set<DermatologistAppointment> getDermatologistAppointments() {
		return dermatologistAppointments;
	}

	public void setDermatologistAppointments(Set<DermatologistAppointment> dermatologistAppointments) {
		this.dermatologistAppointments = dermatologistAppointments;
	}

	public Set<DermatologistComplaint> getDermatologistComplaints() {
		return dermatologistComplaints;
	}

	public void setDermatologistComplaints(Set<DermatologistComplaint> dermatologistComplaints) {
		this.dermatologistComplaints = dermatologistComplaints;
	}

	public Set<DermatologistRating> getDermatologistRatings() {
		return dermatologistRatings;
	}

	public void setDermatologistRatings(Set<DermatologistRating> dermatologistRatings) {
		this.dermatologistRatings = dermatologistRatings;
	}
}

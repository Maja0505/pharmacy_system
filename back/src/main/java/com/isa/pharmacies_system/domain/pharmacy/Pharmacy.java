package com.isa.pharmacies_system.domain.pharmacy;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.rating.PharmacyRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="pharmacies")
public class Pharmacy {
	
	@Id
	@SequenceGenerator(name = "mySeqGenPharmacy", sequenceName = "mySeqPharmacy", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenPharmacy")
	private long id;
	
	@Column(name="pharmacyName", unique = false, nullable = false)
	private String pharmacyName;

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<WorkerSchedule> workerSchedule = new HashSet<WorkerSchedule>();
	
	private Address pharmacyAddress;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForPharmacyAdmin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Dermatologist> dermatologistsInPharmacy = new HashSet<Dermatologist>();

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForPharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pharmacist> pharmacistsInPharmacy = new HashSet<Pharmacist>();
	
	//lista termina za preglede kod dermatologa koje moze da zakaze (STATUS: OPEN) koji se odrzavaju u apoteci
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForDermatologistAppointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermatologistAppointment> dermatologistAppointmentsInPharmacy = new HashSet<DermatologistAppointment>();

	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyComplaint> pharmacyComplaints = new HashSet<PharmacyComplaint>();

	@Column(name="pharmacyAverageRating", unique = false, nullable = false)
	private double pharmacyAverageRating;
	
	@Column(name="pharmacyDescription", unique = false, nullable = false)
	private String pharmacyDescription;
	
	//PRICE LIST -> CENOVNIK LEKOVA + PREGLEDA
	//PHARMACY STORAGE -> SADRZI KOLICINU LEKOVA

	//PROMOCIJE
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForPromotions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Promotions> promotionsForPharmacy = new HashSet<Promotions>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForMedicineReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicineReservation> pharmacyMedicineReservations = new HashSet<MedicineReservation>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyForRating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyRating> pharmacyRatings = new HashSet<PharmacyRating>();
	
	public Pharmacy() {
		
	}


	public Pharmacy(long id, String pharmacyName, Set<WorkerSchedule> workerSchedule, Address pharmacyAddress,
			Set<PharmacyAdmin> pharmacyAdmins, Set<Dermatologist> dermatologistsInPharmacy,
			Set<Pharmacist> pharmacistsInPharmacy, Set<DermatologistAppointment> dermatologistAppointmentsInPharmacy,
			Set<PharmacyComplaint> pharmacyComplaints, double pharmacyAverageRating, String pharmacyDescription,
			Set<Promotions> promotionsForPharmacy, Set<MedicineReservation> pharmacyMedicineReservations,
			Set<PharmacyRating> pharmacyRatings) {
		super();
		this.id = id;
		this.pharmacyName = pharmacyName;
		this.workerSchedule = workerSchedule;
		this.pharmacyAddress = pharmacyAddress;
		//this.pharmacyAdmins = pharmacyAdmins;
		this.dermatologistsInPharmacy = dermatologistsInPharmacy;
		this.pharmacistsInPharmacy = pharmacistsInPharmacy;
		this.dermatologistAppointmentsInPharmacy = dermatologistAppointmentsInPharmacy;
		this.pharmacyComplaints = pharmacyComplaints;
		this.pharmacyAverageRating = pharmacyAverageRating;
		this.pharmacyDescription = pharmacyDescription;
		this.promotionsForPharmacy = promotionsForPharmacy;
		this.pharmacyMedicineReservations = pharmacyMedicineReservations;
		this.pharmacyRatings = pharmacyRatings;
	}

	public Set<PharmacyAdmin> getPharmacyAdmins() {
		return pharmacyAdmins;
	}

	public void setPharmacyAdmins(Set<PharmacyAdmin> pharmacyAdmins) {
		this.pharmacyAdmins = pharmacyAdmins;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Set<WorkerSchedule> getWorkerSchedule() {
		return workerSchedule;
	}
	
	public Set<PharmacyComplaint> getPharmacyComplaints() {
		return pharmacyComplaints;
	}

	public void setPharmacyComplaints(Set<PharmacyComplaint> pharmacyComplaints) {
		this.pharmacyComplaints = pharmacyComplaints;
	}

	public void setWorkerSchedule(Set<WorkerSchedule> workerSchedule) {
		this.workerSchedule = workerSchedule;
	}

	public Address getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(Address pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public Set<Dermatologist> getDermatologistsInPharmacy() {
		return dermatologistsInPharmacy;
	}

	public void setDermatologistsInPharmacy(Set<Dermatologist> dermatologistsInPharmacy) {
		this.dermatologistsInPharmacy = dermatologistsInPharmacy;
	}

	public Set<Pharmacist> getPharmacistsInPharmacy() {
		return pharmacistsInPharmacy;
	}

	public void setPharmacistsInPharmacy(Set<Pharmacist> pharmacistsInPharmacy) {
		this.pharmacistsInPharmacy = pharmacistsInPharmacy;
	}

	public Set<DermatologistAppointment> getDermatologistAppointmentsInPharmacy() {
		return dermatologistAppointmentsInPharmacy;
	}

	public void setDermatologistAppointmentsInPharmacy(Set<DermatologistAppointment> dermatologistAppointmentsInPharmacy) {
		this.dermatologistAppointmentsInPharmacy = dermatologistAppointmentsInPharmacy;
	}

	public double getPharmacyAverageRating() {
		return pharmacyAverageRating;
	}

	public void setPharmacyAverageRating(double pharmacyAverageRating) {
		this.pharmacyAverageRating = pharmacyAverageRating;
	}

	public String getPharmacyDescription() {
		return pharmacyDescription;
	}

	public void setPharmacyDescription(String pharmacyDescription) {
		this.pharmacyDescription = pharmacyDescription;
	}

	public Set<Promotions> getPromotionsForPharmacy() {
		return promotionsForPharmacy;
	}

	public void setPromotionsForPharmacy(Set<Promotions> promotionsForPharmacy) {
		this.promotionsForPharmacy = promotionsForPharmacy;
	}

	public Set<MedicineReservation> getPharmacyMedicineReservations() {
		return pharmacyMedicineReservations;
	}

	public void setPharmacyMedicineReservations(Set<MedicineReservation> pharmacyMedicineReservations) {
		this.pharmacyMedicineReservations = pharmacyMedicineReservations;
	}






	public Set<PharmacyRating> getPharmacyRatings() {
		return pharmacyRatings;
	}






	public void setPharmacyRatings(Set<PharmacyRating> pharmacyRatings) {
		this.pharmacyRatings = pharmacyRatings;
	}

	
	
}

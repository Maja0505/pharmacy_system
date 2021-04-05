package com.isa.pharmacies_system.domain.medicine;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="medicine_reservations")
public class MedicineReservation {
	
	/*
	 * Ako u magacinu imamo 500 brufena, neko rezervise 3 znaci stanje u magacinu je sada 497 i status rezervacije je RESERVED
	 * Ako neko ne pokupi rezervaciju ili otkaze status se postavi na npr. CANCELD ili MISSED i vraca se u magacin
	 * 
	 */
	@Id
	@SequenceGenerator(name = "mySeqGenMedicineReservation", sequenceName = "mySeqGenMedicineReservation", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenMedicineReservation")
	private long id;
	
	//lijek koji se rezevise
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Medicine reservedMedicine;

	//pacijent koji kreira rezervaciju
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Patient patientForMedicineReservation;
	
	//datum preuzimanja lijeka
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "UTC")
	private LocalDate dateOfTakingMedicine;

	//jedan lijek se izdaje u tacno jednoj apoteci
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"pharmacyName","pharmacyAddress","dermatologistsInPharmacy","pharmacistsInPharmacy","dermatologistAppointmentsInPharmacy","medicineReservations","promotionsForPharmacy","pharmacyRatings","workerSchedule","pharmacyComplaints","pharmacyAverageRating","pharmacyDescription","pharmacyMedicineReservations"})
	private Pharmacy pharmacyForMedicineReservation;
	
	//moze biti kreirana, propustena, otkazana i zavrsena
	@Enumerated(EnumType.ORDINAL)
	private StatusOfMedicineReservation statusOfMedicineReservation;

	public MedicineReservation() {
		// TODO Auto-generated constructor stub
	}
	
	public MedicineReservation(long id, Medicine reservedMedicine, Patient patientForMedicineReservation,
							   LocalDate dateOfTakingMedicine, Pharmacy pharmacyForMedicineReservation,
			StatusOfMedicineReservation statusOfMedicineReservation) {
		super();
		this.id = id;
		this.reservedMedicine = reservedMedicine;
		this.patientForMedicineReservation = patientForMedicineReservation;
		this.dateOfTakingMedicine = dateOfTakingMedicine;
		this.pharmacyForMedicineReservation = pharmacyForMedicineReservation;
		this.statusOfMedicineReservation = statusOfMedicineReservation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Medicine getReservedMedicine() {
		return reservedMedicine;
	}

	public void setReservedMedicine(Medicine reservedMedicine) {
		this.reservedMedicine = reservedMedicine;
	}

	public Patient getPatientForMedicineReservation() {
		return patientForMedicineReservation;
	}

	public void setPatientForMedicineReservation(Patient patientForMedicineReservation) {
		this.patientForMedicineReservation = patientForMedicineReservation;
	}

	public LocalDate getDateOfTakingMedicine() {
		return dateOfTakingMedicine;
	}

	public void setDateOfTakingMedicine(LocalDate dateOfTakingMedicine) {
		this.dateOfTakingMedicine = dateOfTakingMedicine;
	}

	public Pharmacy getPharmacyForMedicineReservation() {
		return pharmacyForMedicineReservation;
	}

	public void setPharmacyForMedicineReservation(Pharmacy pharmacyForMedicineReservation) {
		this.pharmacyForMedicineReservation = pharmacyForMedicineReservation;
	}

	public StatusOfMedicineReservation getStatusOfMedicineReservation() {
		return statusOfMedicineReservation;
	}

	public void setStatusOfMedicineReservation(StatusOfMedicineReservation statusOfMedicineReservation) {
		this.statusOfMedicineReservation = statusOfMedicineReservation;
	}
	
	

}

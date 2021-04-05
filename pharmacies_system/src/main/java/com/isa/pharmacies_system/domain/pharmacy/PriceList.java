package com.isa.pharmacies_system.domain.pharmacy;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;

@Entity
@Table(name="price_lists")
public class PriceList {
	@Id
	@SequenceGenerator(name = "mySeqGenPriceList", sequenceName = "mySeqPriceList", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenPriceList")
	private long id;
	
	@Column(name="dermatologistAppointmentPricePerHour", unique=false, nullable=false)
	private double dermatologistAppointmentPricePerHour;
	
	@Column(name="pharmacistAppointmentPricePerHour", unique=false, nullable=false)
	private double pharmacistAppointmentPricePerHour;
	
	@OneToMany(mappedBy = "priceListForMedicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicinePrice> medicinePrices = new HashSet<MedicinePrice>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pharmacy pharmacy;

	public PriceList(){
		
	}
	
	public PriceList(long id, double dermatologistAppointmentPricePerHour, double pharmacistAppointmentPricePerHour,
			Set<MedicinePrice> medicinePrices, Pharmacy pharmacy) {
		super();
		this.id = id;
		this.dermatologistAppointmentPricePerHour = dermatologistAppointmentPricePerHour;
		this.pharmacistAppointmentPricePerHour = pharmacistAppointmentPricePerHour;
		this.medicinePrices = medicinePrices;
		this.pharmacy = pharmacy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getDermatologistAppointmentPricePerHour() {
		return dermatologistAppointmentPricePerHour;
	}

	public void setDermatologistAppointmentPricePerHour(double dermatologistAppointmentPricePerHour) {
		this.dermatologistAppointmentPricePerHour = dermatologistAppointmentPricePerHour;
	}

	public double getPharmacistAppointmentPricePerHour() {
		return pharmacistAppointmentPricePerHour;
	}

	public void setPharmacistAppointmentPricePerHour(double pharmacistAppointmentPricePerHour) {
		this.pharmacistAppointmentPricePerHour = pharmacistAppointmentPricePerHour;
	}

	public Set<MedicinePrice> getMedicinePrices() {
		return medicinePrices;
	}

	public void setMedicinePrices(Set<MedicinePrice> medicinePrices) {
		this.medicinePrices = medicinePrices;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
	
	
	
	
}

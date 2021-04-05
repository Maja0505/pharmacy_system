package com.isa.pharmacies_system.domain.medicine;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;

@Entity
@Table(name="medicine_price")
public class MedicinePrice {
	@Id
	@SequenceGenerator(name = "mySeqGenMedicinePrice", sequenceName = "mySeqMedicinePrice", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenMedicinePrice")
	private long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Instant medicinePriceStartTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Instant medicnePriceEndTime;
	
	@Column(name="medicinePrice", unique=false, nullable=false)
	private double medicinePrice;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicineWithPrices;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PriceList priceListForMedicine;


	public MedicinePrice() {
		
	}
	
	public MedicinePrice(long id, Instant medicinePriceStartTime, Instant medicnePriceEndTime, double medicinePrice,
			Medicine medicineWithPrices, PriceList priceListForMedicine) {
		super();
		this.id = id;
		this.medicinePriceStartTime = medicinePriceStartTime;
		this.medicnePriceEndTime = medicnePriceEndTime;
		this.medicinePrice = medicinePrice;
		this.medicineWithPrices = medicineWithPrices;
		this.priceListForMedicine = priceListForMedicine;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Instant getMedicinePriceStartTime() {
		return medicinePriceStartTime;
	}


	public void setMedicinePriceStartTime(Instant medicinePriceStartTime) {
		this.medicinePriceStartTime = medicinePriceStartTime;
	}


	public Instant getMedicnePriceEndTime() {
		return medicnePriceEndTime;
	}


	public void setMedicnePriceEndTime(Instant medicnePriceEndTime) {
		this.medicnePriceEndTime = medicnePriceEndTime;
	}


	public double getMedicinePrice() {
		return medicinePrice;
	}


	public void setMedicinePrice(double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}


	public Medicine getMedicineWithPrices() {
		return medicineWithPrices;
	}


	public void setMedicineWithPrices(Medicine medicineWithPrices) {
		this.medicineWithPrices = medicineWithPrices;
	}


	public PriceList getPriceListForMedicine() {
		return priceListForMedicine;
	}


	public void setPriceListForMedicine(PriceList priceListForMedicine) {
		this.priceListForMedicine = priceListForMedicine;
	}
	
	

}

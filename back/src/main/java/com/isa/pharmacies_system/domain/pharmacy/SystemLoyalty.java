package com.isa.pharmacies_system.domain.pharmacy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="system_loyalty")
public class SystemLoyalty {
	@Id
	@SequenceGenerator(name = "mySeqGenSystemLoyalty", sequenceName = "mySeqSystemLoyalty", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenSystemLoyalty")
	private long id;
	
	@Column(name="thresholdRegular", unique=false, nullable=false)
	private long thresholdRegular;
	
	@Column(name="discountRegular", unique=false, nullable=false)
	private long discountRegular;
	
	@Column(name="thresholdSilver", unique=false, nullable=false)
	private long thresholdSilver;
	
	@Column(name="discountSilver", unique=false, nullable=false)
	private long discountSilver;
	
	@Column(name="thresholdGold", unique=false, nullable=false)
	private long thresholdGold;
	
	@Column(name="discountGold", unique=false, nullable=false)
	private long discountGold;
	
	@Column(name="pointsForDermatologistAppointment", unique=false, nullable=false)
	private long pointsForDermatologistAppointment;
	
	@Column(name="pointsForPharmacistAppointment", unique=false, nullable=false)
	private long pointsForPharmacistAppointment;
	
	public SystemLoyalty() {
		// TODO Auto-generated constructor stub
	}

	public SystemLoyalty(long id, long thresholdRegular, long discountRegular, long thresholdSilver,
			long discountSilver, long thresholdGold, long discountGold, long pointsForDermatologistAppointment,
			long pointsForPharmacistAppointment) {
		super();
		this.id = id;
		this.thresholdRegular = thresholdRegular;
		this.discountRegular = discountRegular;
		this.thresholdSilver = thresholdSilver;
		this.discountSilver = discountSilver;
		this.thresholdGold = thresholdGold;
		this.discountGold = discountGold;
		this.pointsForDermatologistAppointment = pointsForDermatologistAppointment;
		this.pointsForPharmacistAppointment = pointsForPharmacistAppointment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getThresholdRegular() {
		return thresholdRegular;
	}

	public void setThresholdRegular(long thresholdRegular) {
		this.thresholdRegular = thresholdRegular;
	}

	public long getDiscountRegular() {
		return discountRegular;
	}

	public void setDiscountRegular(long discountRegular) {
		this.discountRegular = discountRegular;
	}

	public long getThresholdSilver() {
		return thresholdSilver;
	}

	public void setThresholdSilver(long thresholdSilver) {
		this.thresholdSilver = thresholdSilver;
	}

	public long getDiscountSilver() {
		return discountSilver;
	}

	public void setDiscountSilver(long discountSilver) {
		this.discountSilver = discountSilver;
	}

	public long getThresholdGold() {
		return thresholdGold;
	}

	public void setThresholdGold(long thresholdGold) {
		this.thresholdGold = thresholdGold;
	}

	public long getDiscountGold() {
		return discountGold;
	}

	public void setDiscountGold(long discountGold) {
		this.discountGold = discountGold;
	}

	public long getPointsForDermatologistAppointment() {
		return pointsForDermatologistAppointment;
	}

	public void setPointsForDermatologistAppointment(long pointsForDermatologistAppointment) {
		this.pointsForDermatologistAppointment = pointsForDermatologistAppointment;
	}

	public long getPointsForPharmacistAppointment() {
		return pointsForPharmacistAppointment;
	}

	public void setPointsForPharmacistAppointment(long pointsForPharmacistAppointment) {
		this.pointsForPharmacistAppointment = pointsForPharmacistAppointment;
	}
	
}

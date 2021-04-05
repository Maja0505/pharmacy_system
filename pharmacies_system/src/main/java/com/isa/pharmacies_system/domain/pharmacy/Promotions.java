package com.isa.pharmacies_system.domain.pharmacy;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="promotions")
public class Promotions {
	@Id
	@SequenceGenerator(name = "mySeqGenPromotions", sequenceName = "mySeqPromotions", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenPromotions")
	private long id;
	
	@Column(name="promotionContent", unique = false, nullable = false)
	private String promotionContent;
	
	//datum kada je kreirana
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Instant promotionBeginDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Instant promotionEndDate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacyForPromotions;

	public Promotions() {
		
	}
	
	public Promotions(long id, String promotionContent, Instant promotionBeginDate, Instant promotionEndDate,
			Pharmacy pharmacyForPromotions) {
		super();
		this.id = id;
		this.promotionContent = promotionContent;
		this.promotionBeginDate = promotionBeginDate;
		this.promotionEndDate = promotionEndDate;
		this.pharmacyForPromotions = pharmacyForPromotions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPromotionContent() {
		return promotionContent;
	}

	public void setPromotionContent(String promotionContent) {
		this.promotionContent = promotionContent;
	}

	public Instant getPromotionBeginDate() {
		return promotionBeginDate;
	}

	public void setPromotionBeginDate(Instant promotionBeginDate) {
		this.promotionBeginDate = promotionBeginDate;
	}

	public Instant getPromotionEndDate() {
		return promotionEndDate;
	}

	public void setPromotionEndDate(Instant promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}

	public Pharmacy getPharmacyForPromotions() {
		return pharmacyForPromotions;
	}

	public void setPharmacyForPromotions(Pharmacy pharmacyForPromotions) {
		this.pharmacyForPromotions = pharmacyForPromotions;
	}
	
	
	
	

}

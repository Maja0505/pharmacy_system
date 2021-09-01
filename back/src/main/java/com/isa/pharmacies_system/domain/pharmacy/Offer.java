package com.isa.pharmacies_system.domain.pharmacy;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.user.Supplier;

@Entity
@Table(name="offers")
public class Offer {
	
	@Id
	@SequenceGenerator(name = "mySeqGenOffer", sequenceName = "mySeqOffer", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenOffer")
	private long id;
	
	@Column(name="fullPrice", unique=false, nullable=false)
	private double fullPrice;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deliveryDate;
	
	//dobavljac
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Supplier offerSupplier;
	
	//id narudzbenice
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Order orderForOffer;
	
	@Enumerated(EnumType.ORDINAL)
	private StateOfOffer stateOfOffer;

	public Offer() {
		
	}
	
	public Offer(long id, double fullPrice, LocalDateTime deliveryDate, Supplier offerSupplier, Order orderForOffer,
			StateOfOffer stateOfOffer) {
		super();
		this.id = id;
		this.fullPrice = fullPrice;
		this.deliveryDate = deliveryDate;
		this.offerSupplier = offerSupplier;
		this.orderForOffer = orderForOffer;
		this.stateOfOffer = stateOfOffer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Supplier getOfferSupplier() {
		return offerSupplier;
	}

	public void setOfferSupplier(Supplier offerSupplier) {
		this.offerSupplier = offerSupplier;
	}

	public Order getOrderForOffer() {
		return orderForOffer;
	}

	public void setOrderForOffer(Order orderForOffer) {
		this.orderForOffer = orderForOffer;
	}

	public StateOfOffer getStateOfOffer() {
		return stateOfOffer;
	}

	public void setStateOfOffer(StateOfOffer stateOfOffer) {
		this.stateOfOffer = stateOfOffer;
	}
	
	
	
	
}

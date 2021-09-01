package com.isa.pharmacies_system.domain.pharmacy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@SequenceGenerator(name = "mySeqGenOrder", sequenceName = "mySeqOrder", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenOrder")
	private long id;

	// spisak lekova sa kolicinama
	@JsonManagedReference
	@OneToMany(mappedBy = "orderWithItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	// rok do kojeg dobavljaci mogu da daju svoje ponude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime orderEndDate;

	// admin apoteke
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PharmacyAdmin pharmacyAdminForOrder;

	// spisak svih ponuda za narudzbenicu
	@JsonManagedReference
	@OneToMany(mappedBy = "orderForOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Offer> listOfOffersForOrder = new HashSet<Offer>();

	@Enumerated(EnumType.ORDINAL)
	private StateOfOrder stateOfOrder;

	public Order() {

	}

	public Order(long id, Set<OrderItem> orderItems, LocalDateTime orderEndDate, PharmacyAdmin pharmacyAdminForOrder,
			Set<Offer> listOfOffersForOrder, StateOfOrder stateOfOrder) {
		super();
		this.id = id;
		this.orderItems = orderItems;
		this.orderEndDate = orderEndDate;
		this.pharmacyAdminForOrder = pharmacyAdminForOrder;
		this.listOfOffersForOrder = listOfOffersForOrder;
		this.stateOfOrder = stateOfOrder;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(LocalDateTime orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public PharmacyAdmin getPharmacyAdminForOrder() {
		return pharmacyAdminForOrder;
	}

	public void setPharmacyAdminForOrder(PharmacyAdmin pharmacyAdminForOrder) {
		this.pharmacyAdminForOrder = pharmacyAdminForOrder;
	}

	public Set<Offer> getListOfOffersForOrder() {
		return listOfOffersForOrder;
	}

	public void setListOfOffersForOrder(Set<Offer> listOfOffersForOrder) {
		this.listOfOffersForOrder = listOfOffersForOrder;
	}

	public StateOfOrder getStateOfOrder() {
		return stateOfOrder;
	}

	public void setStateOfOrder(StateOfOrder stateOfOrder) {
		this.stateOfOrder = stateOfOrder;
	}

}

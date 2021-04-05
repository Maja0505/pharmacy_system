package com.isa.pharmacies_system.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.pharmacy.Order;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

@Entity
@Table(name="pharmacy_admins")
public class PharmacyAdmin extends Users{
	
	
	//spisak svih narudzbenica
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacyAdminForOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Order> listOfOrders = new HashSet<Order>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacyForPharmacyAdmin;

	public PharmacyAdmin() {
		
	}
	
	

	public PharmacyAdmin(Set<Order> listOfOrders, Pharmacy pharmacyForPharmacyAdmin) {
		super();
		this.listOfOrders = listOfOrders;
		this.pharmacyForPharmacyAdmin = pharmacyForPharmacyAdmin;
	}



	public Pharmacy getPharmacyForPharmacyAdmin() {
		return pharmacyForPharmacyAdmin;
	}



	public void setPharmacyForPharmacyAdmin(Pharmacy pharmacyForPharmacyAdmin) {
		this.pharmacyForPharmacyAdmin = pharmacyForPharmacyAdmin;
	}



	public Set<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(Set<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}
	
	
	

}

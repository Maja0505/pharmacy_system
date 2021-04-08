package com.isa.pharmacies_system.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.pharmacy.Offer;

@Entity
@Table(name="suppliers")
public class Supplier extends Users {
	
	//lista ponuda
	@JsonManagedReference
	@OneToMany(mappedBy = "offerSupplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Offer> listOfOffers = new HashSet<Offer>();

	public Supplier() {
		
	}
	
	public Supplier(Set<Offer> listOfOffers) {
		super();
		this.listOfOffers = listOfOffers;
	}

	public Set<Offer> getListOfOffers() {
		return listOfOffers;
	}

	public void setListOfOffers(Set<Offer> listOfOffers) {
		this.listOfOffers = listOfOffers;
	}
	
	
	
	
	
}

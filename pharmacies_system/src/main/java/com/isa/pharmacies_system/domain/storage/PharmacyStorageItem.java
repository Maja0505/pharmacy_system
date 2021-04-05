package com.isa.pharmacies_system.domain.storage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.medicine.Item;

@Entity
@Table(name="pharmacy_storage_items")
public class PharmacyStorageItem extends Item {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PharmacyStorage pharmacyStorageWithItem;
	
	public PharmacyStorageItem() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyStorageItem(PharmacyStorage pharmacyStorageWithItem) {
		super();
		this.pharmacyStorageWithItem = pharmacyStorageWithItem;
	}

	public PharmacyStorage getPharmacyStorageWithItem() {
		return pharmacyStorageWithItem;
	}

	public void setPharmacyStorageWithItem(PharmacyStorage pharmacyStorageWithItem) {
		this.pharmacyStorageWithItem = pharmacyStorageWithItem;
	}
	
	
}

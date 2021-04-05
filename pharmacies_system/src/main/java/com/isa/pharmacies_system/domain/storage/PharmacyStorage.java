package com.isa.pharmacies_system.domain.storage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

@Entity
@Table(name="pharmacy_storages")
public class PharmacyStorage extends Storage {
	
	//proveriti da li je bolja verzija sa MedicinePrice ili samo Medicine
	@OneToMany(mappedBy = "pharmacyStorageWithItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyStorageItem> pharmacyStorageItems = new HashSet<PharmacyStorageItem>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pharmacy pharmacy;

	public PharmacyStorage() {
		
	}

	public PharmacyStorage(Set<PharmacyStorageItem> pharmacyStorageItems, Pharmacy pharmacy) {
		super();
		this.pharmacyStorageItems = pharmacyStorageItems;
		this.pharmacy = pharmacy;
	}

	public Set<PharmacyStorageItem> getPharmacyStorageItems() {
		return pharmacyStorageItems;
	}

	public void setPharmacyStorageItems(Set<PharmacyStorageItem> pharmacyStorageItems) {
		this.pharmacyStorageItems = pharmacyStorageItems;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
	
}

package com.isa.pharmacies_system.domain.storage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.user.Supplier;

@Entity
@Table(name="supplier_storages")
public class SupplierStorage extends Storage {

	@JsonManagedReference
	@OneToMany(mappedBy = "supplierStorageWithItem",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SupplierStorageItem> supplierStorageItems = new HashSet<SupplierStorageItem>();
		
	@OneToOne(cascade = CascadeType.ALL)
	private Supplier supplier;
	
	
	public SupplierStorage() {
	}
	
	public SupplierStorage(Set<SupplierStorageItem> supplierStorageItems, Supplier supplier) {
		super();
		this.supplierStorageItems = supplierStorageItems;
		this.supplier = supplier;
	}




	public Set<SupplierStorageItem> getSupplierStorageItems() {
		return supplierStorageItems;
	}


	public void setSupplierStorageItems(Set<SupplierStorageItem> supplierStorageItems) {
		this.supplierStorageItems = supplierStorageItems;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	

		
}

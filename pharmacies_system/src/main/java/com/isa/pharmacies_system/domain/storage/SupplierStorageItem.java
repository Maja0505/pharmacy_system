package com.isa.pharmacies_system.domain.storage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.pharmacies_system.domain.medicine.Item;


@Entity
@Table(name="supplier_storage_items")
public class SupplierStorageItem extends Item {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SupplierStorage supplierStorageWithItem;

	public SupplierStorageItem() {
		// TODO Auto-generated constructor stub
	}

	public SupplierStorage getSupplierStorageWithItems() {
		return supplierStorageWithItem;
	}

	public void setSupplierStorageWithItems(SupplierStorage supplierStorageWithItems) {
		this.supplierStorageWithItem = supplierStorageWithItems;
	}
}

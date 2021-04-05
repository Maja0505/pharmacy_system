package com.isa.pharmacies_system.domain.storage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.medicine.Item;


@Entity
@Table(name="supplier_storage_items")
public class SupplierStorageItem extends Item {

	public SupplierStorageItem() {
		// TODO Auto-generated constructor stub
	}

}

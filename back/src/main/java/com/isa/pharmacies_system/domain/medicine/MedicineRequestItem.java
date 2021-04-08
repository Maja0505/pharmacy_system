package com.isa.pharmacies_system.domain.medicine;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicine_request_items")
public class MedicineRequestItem extends Item {
	

	public MedicineRequestItem() {
		
	}
	
}

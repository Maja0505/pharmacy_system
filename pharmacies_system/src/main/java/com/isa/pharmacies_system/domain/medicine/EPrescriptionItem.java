package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="eprescription_items")
public class EPrescriptionItem extends Item {

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EPrescription ePrescriton;
	
		public EPrescriptionItem() {
		
	}
	
	public EPrescriptionItem(EPrescription ePrescriton) {
		super();
		this.ePrescriton = ePrescriton;
	}

	public EPrescription getePrescriton() {
		return ePrescriton;
	}

	public void setePrescriton(EPrescription ePrescriton) {
		this.ePrescriton = ePrescriton;
	}

	
}

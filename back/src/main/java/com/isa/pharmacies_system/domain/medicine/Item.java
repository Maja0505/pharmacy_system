package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.*;

@Entity
@Table(name="items")
@Inheritance(strategy=JOINED)
public class Item {
	@Id
	@SequenceGenerator(name = "mySeqGenItem", sequenceName = "mySeqItem", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenItem")
	private long id;

	@Version
	private Long version;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicineItem;
	
	@Column(name="medicineAmount", unique=false, nullable=false)
	private long medicineAmount;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfItem typeOfItem;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(long id, Medicine medicineItem, long medicineAmount, TypeOfItem typeOfItem) {
		super();
		this.id = id;
		this.medicineItem = medicineItem;
		this.medicineAmount = medicineAmount;
		this.typeOfItem = typeOfItem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Medicine getMedicineItem() {
		return medicineItem;
	}

	public void setMedicineItem(Medicine medicineItem) {
		this.medicineItem = medicineItem;
	}

	public long getMedicineAmount() {
		return medicineAmount;
	}

	public void setMedicineAmount(long medicineAmount) {
		this.medicineAmount = medicineAmount;
	}

	public TypeOfItem getTypeOfItem() {
		return typeOfItem;
	}

	public void setTypeOfItem(TypeOfItem typeOfItem) {
		this.typeOfItem = typeOfItem;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}

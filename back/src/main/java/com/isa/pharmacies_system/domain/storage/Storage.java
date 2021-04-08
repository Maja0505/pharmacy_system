package com.isa.pharmacies_system.domain.storage;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="storages")
@Inheritance(strategy=JOINED)
public class Storage {
	@Id
	@SequenceGenerator(name = "mySeqGenStorage", sequenceName = "mySeqStorage", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenStorage")
	private long id;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfStorage typeOfStorage;

	public Storage() {
		// TODO Auto-generated constructor stub
	}
	
	public Storage(long id,TypeOfStorage typeOfStorage) {
		super();
		this.id = id;
		this.typeOfStorage=typeOfStorage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TypeOfStorage getTypeOfStorage() {
		return typeOfStorage;
	}

	public void setTypeOfStorage(TypeOfStorage typeOfStorage) {
		this.typeOfStorage = typeOfStorage;
	}
	
	
	
	
	
}

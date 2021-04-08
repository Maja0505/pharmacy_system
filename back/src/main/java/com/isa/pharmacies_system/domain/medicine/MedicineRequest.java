package com.isa.pharmacies_system.domain.medicine;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="medicine_requests")
public class MedicineRequest {
	@Id
	@SequenceGenerator(name = "mySeqGenMedicineRequest", sequenceName = "mySeqMedicineRequest", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenMedicineRequest")
	private long id;

	//prvo se kreira medicineRequestItem pa medicineRequest
	@OneToOne(cascade = CascadeType.ALL)
	private MedicineRequestItem medicineRequestItem;
	
	@Enumerated(EnumType.ORDINAL)
	private StateOfMedicineRequest stateOfMedicineRequest;
	
	//treba poslati svim administratorima te apoteke
	
	
	public MedicineRequest() {
		
	}

	public MedicineRequest(long id, MedicineRequestItem medicineRequestItem,
		StateOfMedicineRequest stateOfMedicineRequest) {
	super();
	this.id = id;
	this.medicineRequestItem = medicineRequestItem;
	this.stateOfMedicineRequest = stateOfMedicineRequest;
	
	
	}
	

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public MedicineRequestItem getMedicineRequestItem() {
		return medicineRequestItem;
	}

	public void setMedicineRequestItem(MedicineRequestItem medicineRequestItem) {
		this.medicineRequestItem = medicineRequestItem;
	}

	public StateOfMedicineRequest getStateOfMedicineRequest() {
		return stateOfMedicineRequest;
	}

	public void setStateOfMedicineRequest(StateOfMedicineRequest stateOfMedicineRequest) {
		this.stateOfMedicineRequest = stateOfMedicineRequest;
	}
	
	
	
	
	
	

}

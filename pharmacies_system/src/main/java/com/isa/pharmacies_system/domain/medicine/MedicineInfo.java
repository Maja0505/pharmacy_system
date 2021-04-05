package com.isa.pharmacies_system.domain.medicine;

import static javax.persistence.InheritanceType.JOINED;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="medicine_infos")
@Inheritance(strategy=JOINED)
public class MedicineInfo {
	@Id
	@SequenceGenerator(name = "mySeqGenMedicineInfo", sequenceName = "mySeqMedicineInfo", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenMedicineInfo")
	private long id;
	
	@Column(name="medicine_name", unique=true, nullable=false)
	private String medicineName;
	
	@Column(name="medicine_code", unique=true, nullable=false)
	private String medicineCode;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfMedicine typeOfMedicine;
	
	@Enumerated(EnumType.ORDINAL)
	private FormOfMedicine formOfMedicine;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	@Column(name="manufacturer_of_medicine", unique=false, nullable=false)
	private String manufacturerOfMedicine;
	
	@Enumerated(EnumType.ORDINAL)
	private PrescriptionRegime prescriptionRegime;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<MedicineInfo> alternativeMedicines = new HashSet<MedicineInfo>();
	
	@Column(name="notes", unique=false, nullable=false)
	private String notes;

	public MedicineInfo() {
		
	}
	
	public MedicineInfo(long id, String medicineName, String medicineCode, TypeOfMedicine typeOfMedicine,
			FormOfMedicine formOfMedicine, Set<Ingredient> ingredients, String manufacturerOfMedicine,
			PrescriptionRegime prescriptionRegime, Set<MedicineInfo> alternativeMedicines, String notes) {
		super();
		this.id = id;
		this.medicineName = medicineName;
		this.medicineCode = medicineCode;
		this.typeOfMedicine = typeOfMedicine;
		this.formOfMedicine = formOfMedicine;
		this.ingredients = ingredients;
		this.manufacturerOfMedicine = manufacturerOfMedicine;
		this.prescriptionRegime = prescriptionRegime;
		this.alternativeMedicines = alternativeMedicines;
		this.notes = notes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public TypeOfMedicine getTypeOfMedicine() {
		return typeOfMedicine;
	}

	public void setTypeOfMedicine(TypeOfMedicine typeOfMedicine) {
		this.typeOfMedicine = typeOfMedicine;
	}

	public FormOfMedicine getFormOfMedicine() {
		return formOfMedicine;
	}

	public void setFormOfMedicine(FormOfMedicine formOfMedicine) {
		this.formOfMedicine = formOfMedicine;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getManufacturerOfMedicine() {
		return manufacturerOfMedicine;
	}

	public void setManufacturerOfMedicine(String manufacturerOfMedicine) {
		this.manufacturerOfMedicine = manufacturerOfMedicine;
	}

	public PrescriptionRegime getPrescriptionRegime() {
		return prescriptionRegime;
	}

	public void setPrescriptionRegime(PrescriptionRegime prescriptionRegime) {
		this.prescriptionRegime = prescriptionRegime;
	}

	public Set<MedicineInfo> getAlternativeMedicines() {
		return alternativeMedicines;
	}

	public void setAlternativeMedicines(Set<MedicineInfo> alternativeMedicines) {
		this.alternativeMedicines = alternativeMedicines;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	

}

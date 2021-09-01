package com.isa.pharmacies_system.DTO;

import java.util.List;
import java.util.Set;

import com.isa.pharmacies_system.domain.medicine.FormOfMedicine;
import com.isa.pharmacies_system.domain.medicine.Ingredient;
import com.isa.pharmacies_system.domain.medicine.TypeOfMedicine;

public class MedicineNewDTO {
	private String name;
	private String code;
	private TypeOfMedicine type;
	private String notes;
	private Set<Ingredient> ingredients;
	private List<String> namesOfAlternativeMedicines;
	private FormOfMedicine form;
	private Boolean onRecept;
	private String manufacturerName;
	private long points;
	
	public MedicineNewDTO() {}

	public MedicineNewDTO(String name, String code, TypeOfMedicine type, String notes, Set<Ingredient> ingredients,
			List<String> namesOfAlternativeMedicines, FormOfMedicine form, Boolean onRecept, String manufacturerName, long points) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
		this.notes = notes;
		this.ingredients = ingredients;
		this.namesOfAlternativeMedicines = namesOfAlternativeMedicines;
		this.form = form;
		this.onRecept = onRecept;
		this.manufacturerName = manufacturerName;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TypeOfMedicine getType() {
		return type;
	}

	public void setType(TypeOfMedicine type) {
		this.type = type;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getNamesOfAlternativeMedicines() {
		return namesOfAlternativeMedicines;
	}

	public void setNamesOfAlternativeMedicines(List<String> namesOfAlternativeMedicines) {
		this.namesOfAlternativeMedicines = namesOfAlternativeMedicines;
	}

	public FormOfMedicine getForm() {
		return form;
	}

	public void setForm(FormOfMedicine form) {
		this.form = form;
	}

	public Boolean getOnRecept() {
		return onRecept;
	}

	public void setOnRecept(Boolean onRecept) {
		this.onRecept = onRecept;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}
	
}

package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.medicine.FormOfMedicine;
import com.isa.pharmacies_system.domain.medicine.Ingredient;
import com.isa.pharmacies_system.domain.medicine.TypeOfMedicine;

import java.util.Set;

public class MedicineForRecipeDTO {

    private Long medicineId;
    private Long itemId;
    private String medicineName;
    private Set<Ingredient> ingredients;
    private String manufacturerOfMedicine;
    private String notes;
    private Long medicineAmount;
    private FormOfMedicine formOfMedicine;
    private TypeOfMedicine typeOfMedicine;


    public MedicineForRecipeDTO() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Long getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(Long medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    public FormOfMedicine getFormOfMedicine() {
        return formOfMedicine;
    }

    public void setFormOfMedicine(FormOfMedicine formOfMedicine) {
        this.formOfMedicine = formOfMedicine;
    }

    public TypeOfMedicine getTypeOfMedicine() {
        return typeOfMedicine;
    }

    public void setTypeOfMedicine(TypeOfMedicine typeOfMedicine) {
        this.typeOfMedicine = typeOfMedicine;
    }
}

package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.medicine.Ingredient;

import java.util.List;

public class MedicineForRecipeDTO {

    private Long medicineId;
    private String medicineName;
    private List<Ingredient> ingredients;
    private String manufacturerOfMedicine;
    private String notes;

    public MedicineForRecipeDTO() {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
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
}

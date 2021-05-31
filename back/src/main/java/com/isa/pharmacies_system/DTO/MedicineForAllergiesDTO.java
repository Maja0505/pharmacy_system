package com.isa.pharmacies_system.DTO;

public class MedicineForAllergiesDTO {

    private Long medicineId;
    private String medicineName;

    public MedicineForAllergiesDTO() {
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
}

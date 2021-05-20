package com.isa.pharmacies_system.DTO;

public class RatingMedicineDTO extends RatingDTO{

    private Long medicineId;
    private String medicineName;

    public RatingMedicineDTO() {
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

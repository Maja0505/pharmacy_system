package com.isa.pharmacies_system.DTO;

public class EPrescriptionItemDTO {

    private Long medicineAmount;
    private Long medicineId;
    private String medicineName;

    public EPrescriptionItemDTO() {
    }

    public Long getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(Long medicineAmount) {
        this.medicineAmount = medicineAmount;
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

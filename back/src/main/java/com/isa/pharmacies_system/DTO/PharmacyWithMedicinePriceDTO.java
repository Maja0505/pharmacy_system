package com.isa.pharmacies_system.DTO;

public class PharmacyWithMedicinePriceDTO extends PharmacyDTO{

    private Double medicinePrice;

    public PharmacyWithMedicinePriceDTO() {
    }

    public Double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
}

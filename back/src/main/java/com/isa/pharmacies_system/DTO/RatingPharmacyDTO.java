package com.isa.pharmacies_system.DTO;

public class RatingPharmacyDTO extends RatingDTO{

    private Long pharmacyId;
    private String pharmacyName;

    public RatingPharmacyDTO() {
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}

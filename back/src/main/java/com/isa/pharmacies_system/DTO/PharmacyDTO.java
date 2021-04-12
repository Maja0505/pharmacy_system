package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class PharmacyDTO {

    private long id;
    private String pharmacyName;
    private Address pharmacyAddress;
    private double pharmacyAverageRating;

    public PharmacyDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Address getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(Address pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public double getPharmacyAverageRating() {
        return pharmacyAverageRating;
    }

    public void setPharmacyAverageRating(double pharmacyAverageRating) {
        this.pharmacyAverageRating = pharmacyAverageRating;
    }
}

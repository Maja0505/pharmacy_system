package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class PharmacyProfileDTO {

    private long id;
    private String pharmacyName;
    private String streetName;
    private String streetNumber;
    private String country;
    private String cityForPharmacy;
    private double pharmacyAverageRating;
    private Double dermatologistPerHour;
    private Double pharmacistPerHour;
    private String pharmacyDescription;

    public PharmacyProfileDTO() {
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityForPharmacy() {
        return cityForPharmacy;
    }

    public void setCityForPharmacy(String cityForPharmacy) {
        this.cityForPharmacy = cityForPharmacy;
    }

    public double getPharmacyAverageRating() {
        return pharmacyAverageRating;
    }

    public void setPharmacyAverageRating(double pharmacyAverageRating) {
        this.pharmacyAverageRating = pharmacyAverageRating;
    }

    public Double getDermatologistPerHour() {
        return dermatologistPerHour;
    }

    public void setDermatologistPerHour(Double dermatologistPerHour) {
        this.dermatologistPerHour = dermatologistPerHour;
    }

    public Double getPharmacistPerHour() {
        return pharmacistPerHour;
    }

    public void setPharmacistPerHour(Double pharmacistPerHour) {
        this.pharmacistPerHour = pharmacistPerHour;
    }

    public String getPharmacyDescription() {
        return pharmacyDescription;
    }

    public void setPharmacyDescription(String pharmacyDescription) {
        this.pharmacyDescription = pharmacyDescription;
    }
}

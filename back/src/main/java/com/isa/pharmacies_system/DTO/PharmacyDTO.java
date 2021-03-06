package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;

public class PharmacyDTO extends PharmacyInfoDTO{

    private long id;
    private String pharmacyName;
    private Address pharmacyAddress;
    private String cityForPharmacy; //zbog sortiranja
    private double pharmacyAverageRating;
    private PriceListForAppointmentDTO priceListForAppointmentDTO;


    public PharmacyDTO() {
    	super();
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

    public PriceListForAppointmentDTO getPriceListForAppointmentDTO() {
        return priceListForAppointmentDTO;
    }

    public void setPriceListForAppointmentDTO(PriceListForAppointmentDTO priceListForAppointmentDTO) {
        this.priceListForAppointmentDTO = priceListForAppointmentDTO;
    }

    public String getCityForPharmacy() {
        return cityForPharmacy;
    }

    public void setCityForPharmacy(String cityForPharmacy) {
        this.cityForPharmacy = cityForPharmacy;
    }
}

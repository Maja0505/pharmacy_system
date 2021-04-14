package com.isa.pharmacies_system.DTO;

public class PriceListForAppointmentDTO {

    private double dermatologistAppointmentPricePerHour;
    private double pharmacistAppointmentPricePerHour;

    public PriceListForAppointmentDTO() {
    }

    public PriceListForAppointmentDTO(double dermatologistAppointmentPricePerHour, double pharmacistAppointmentPricePerHour) {
        this.dermatologistAppointmentPricePerHour = dermatologistAppointmentPricePerHour;
        this.pharmacistAppointmentPricePerHour = pharmacistAppointmentPricePerHour;
    }

    public double getDermatologistAppointmentPricePerHour() {
        return dermatologistAppointmentPricePerHour;
    }

    public void setDermatologistAppointmentPricePerHour(double dermatologistAppointmentPricePerHour) {
        this.dermatologistAppointmentPricePerHour = dermatologistAppointmentPricePerHour;
    }

    public double getPharmacistAppointmentPricePerHour() {
        return pharmacistAppointmentPricePerHour;
    }

    public void setPharmacistAppointmentPricePerHour(double pharmacistAppointmentPricePerHour) {
        this.pharmacistAppointmentPricePerHour = pharmacistAppointmentPricePerHour;
    }
}

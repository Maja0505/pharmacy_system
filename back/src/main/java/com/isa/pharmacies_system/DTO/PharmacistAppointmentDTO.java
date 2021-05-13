package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class PharmacistAppointmentDTO {

    private long id;
    private double appointmentPrice;
    private LocalDateTime pharmacistAppointmentStartTime;
    private long pharmacistAppointmentDuration;
    private UserPersonalInfoDTO pharmacistForAppointment;
    private PharmacyDTO pharmacyForPharmacistAppointment;

    public PharmacistAppointmentDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(double appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public LocalDateTime getPharmacistAppointmentStartTime() {
        return pharmacistAppointmentStartTime;
    }

    public void setPharmacistAppointmentStartTime(LocalDateTime pharmacistAppointmentStartTime) {
        this.pharmacistAppointmentStartTime = pharmacistAppointmentStartTime;
    }

    public long getPharmacistAppointmentDuration() {
        return pharmacistAppointmentDuration;
    }

    public void setPharmacistAppointmentDuration(long pharmacistAppointmentDuration) {
        this.pharmacistAppointmentDuration = pharmacistAppointmentDuration;
    }

    public UserPersonalInfoDTO getPharmacistForAppointment() {
        return pharmacistForAppointment;
    }

    public void setPharmacistForAppointment(UserPersonalInfoDTO pharmacistForAppointment) {
        this.pharmacistForAppointment = pharmacistForAppointment;
    }

    public PharmacyDTO getPharmacyForPharmacistAppointment() {
        return pharmacyForPharmacistAppointment;
    }

    public void setPharmacyForPharmacistAppointment(PharmacyDTO pharmacyForDermatologistAppointment) {
        this.pharmacyForPharmacistAppointment = pharmacyForDermatologistAppointment;
    }
}

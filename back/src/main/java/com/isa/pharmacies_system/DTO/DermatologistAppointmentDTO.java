package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Dermatologist;

import java.time.LocalDateTime;

public class DermatologistAppointmentDTO {

    private long id;
    private double appointmentPrice;
    private LocalDateTime dermatologistAppointmentStartTime;
    private LocalDateTime dermatologistAppointmentEndTime;
    private UserPersonalInfoDTO dermatologistForAppointment;
    private PharmacyDTO pharmacyForDermatologistAppointment;

    public DermatologistAppointmentDTO() {
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

    public LocalDateTime getDermatologistAppointmentStartTime() {
        return dermatologistAppointmentStartTime;
    }

    public void setDermatologistAppointmentStartTime(LocalDateTime dermatologistAppointmentStartTime) {
        this.dermatologistAppointmentStartTime = dermatologistAppointmentStartTime;
    }

    public LocalDateTime getDermatologistAppointmentEndTime() {
        return dermatologistAppointmentEndTime;
    }

    public void setDermatologistAppointmentEndTime(LocalDateTime dermatologistAppointmentEndTime) {
        this.dermatologistAppointmentEndTime = dermatologistAppointmentEndTime;
    }

    public UserPersonalInfoDTO getDermatologistForAppointment() {
        return dermatologistForAppointment;
    }

    public void setDermatologistForAppointment(UserPersonalInfoDTO dermatologistForAppointment) {
        this.dermatologistForAppointment = dermatologistForAppointment;
    }

    public PharmacyDTO getPharmacyForDermatologistAppointment() {
        return pharmacyForDermatologistAppointment;
    }

    public void setPharmacyForDermatologistAppointment(PharmacyDTO pharmacyForDermatologistAppointment) {
        this.pharmacyForDermatologistAppointment = pharmacyForDermatologistAppointment;
    }
}

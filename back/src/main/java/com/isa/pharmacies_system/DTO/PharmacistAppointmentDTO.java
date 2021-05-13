package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class PharmacistAppointmentDTO {

    private long id;
    private double appointmentPrice;
    private LocalDateTime pharmacistAppointmentStartTime;
    private LocalDateTime pharmacistAppointmentEndTime;
    private long pharmacistAppointmentDuration;
    private UserPersonalInfoDTO pharmacistForAppointment;
    private PharmacyDTO pharmacyForDermatologistAppointment;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientPhoneNumber;

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

    public PharmacyDTO getPharmacyForDermatologistAppointment() {
        return pharmacyForDermatologistAppointment;
    }

    public void setPharmacyForDermatologistAppointment(PharmacyDTO pharmacyForDermatologistAppointment) {
        this.pharmacyForDermatologistAppointment = pharmacyForDermatologistAppointment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public LocalDateTime getPharmacistAppointmentEndTime() {
        return pharmacistAppointmentEndTime;
    }

    public void setPharmacistAppointmentEndTime(LocalDateTime pharmacistAppointmentEndTime) {
        this.pharmacistAppointmentEndTime = pharmacistAppointmentEndTime;
    }
}

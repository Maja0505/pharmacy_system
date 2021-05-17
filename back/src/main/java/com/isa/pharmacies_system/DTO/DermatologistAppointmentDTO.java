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
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientPhoneNumber;
    private String location;
    private String subject;
    private String pharmacyName;
    private int colorId;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}

package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class PharmacistAppointmentDTO {

    private long Id;
    private double appointmentPrice;
    private String Subject;
    private String Location;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private LocalDateTime pharmacistAppointmentStartTime;
    private LocalDateTime pharmacistAppointmentEndTime;
    private long pharmacistAppointmentDuration;
    private UserPersonalInfoDTO pharmacistForAppointment;
    private PharmacyDTO pharmacyForPharmacistAppointment;
    private Long PatientId;
    private String PatientFirstName;
    private String PatientLastName;
    private String PatientEmail;
    private String PatientPhoneNumber;
    private String PharmacyName;
    private int ColorId;

    public PharmacistAppointmentDTO() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public double getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(double appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        StartTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        EndTime = endTime;
    }

    public LocalDateTime getPharmacistAppointmentStartTime() {
        return pharmacistAppointmentStartTime;
    }

    public void setPharmacistAppointmentStartTime(LocalDateTime pharmacistAppointmentStartTime) {
        this.pharmacistAppointmentStartTime = pharmacistAppointmentStartTime;
    }

    public LocalDateTime getPharmacistAppointmentEndTime() {
        return pharmacistAppointmentEndTime;
    }

    public void setPharmacistAppointmentEndTime(LocalDateTime pharmacistAppointmentEndTime) {
        this.pharmacistAppointmentEndTime = pharmacistAppointmentEndTime;
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

    public void setPharmacyForPharmacistAppointment(PharmacyDTO pharmacyForPharmacistAppointment) {
        this.pharmacyForPharmacistAppointment = pharmacyForPharmacistAppointment;
    }

    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long patientId) {
        PatientId = patientId;
    }

    public String getPatientFirstName() {
        return PatientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        PatientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return PatientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        PatientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public String getPatientPhoneNumber() {
        return PatientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        PatientPhoneNumber = patientPhoneNumber;
    }

    public String getPharmacyName() {
        return PharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        PharmacyName = pharmacyName;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }
}

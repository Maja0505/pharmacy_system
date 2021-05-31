package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PharmacistAppointmentDTO {

    private long id;
    private double appointmentPrice;
    private String subject;
    private String location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pharmacistAppointmentStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pharmacistAppointmentEndTime;
    private long pharmacistAppointmentDuration;
    private UserPersonalInfoDTO pharmacistForAppointment;
    private PharmacyDTO pharmacyForPharmacistAppointment;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientPhoneNumber;
    private Long pharmacyId;
    private String pharmacyName;
    private int colorId;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}

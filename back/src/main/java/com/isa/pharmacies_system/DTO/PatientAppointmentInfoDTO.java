package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class PatientAppointmentInfoDTO {

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;
    private double appointmentDuration;
    private double appointmentPrice;

    public PatientAppointmentInfoDTO() {
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

    public LocalDateTime getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(LocalDateTime appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public LocalDateTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(LocalDateTime appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public double getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(double appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public double getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(double appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }
}

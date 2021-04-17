package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class AppointmentScheduleByStaffDTO {

    private Long staffId;
    private Long pharmacyId;
    private Long patientId;
    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;
    private Long appointmentDuration;
    private LocalDateTime staffWorkStartTime;
    private LocalDateTime staffWorkEndTime;

    public AppointmentScheduleByStaffDTO() {
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public Long getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(Long appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public LocalDateTime getStaffWorkStartTime() {
        return staffWorkStartTime;
    }

    public void setStaffWorkStartTime(LocalDateTime staffWorkStartTime) {
        this.staffWorkStartTime = staffWorkStartTime;
    }

    public LocalDateTime getStaffWorkEndTime() {
        return staffWorkEndTime;
    }

    public void setStaffWorkEndTime(LocalDateTime staffWorkEndTime) {
        this.staffWorkEndTime = staffWorkEndTime;
    }
}

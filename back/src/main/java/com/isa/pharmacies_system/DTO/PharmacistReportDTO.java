package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;

import java.time.LocalDateTime;

public class PharmacistReportDTO {
    private Long id;
    private Long pharmacyId;
    private Long pharmacistId;
    private String pharmacyName;
    private String pharmacistFirstName;
    private String pharmacistLastName;
    private String reportInfo;
    private Long appointmentId;
    private LocalDateTime pharmacistAppointmentStartTime;
    private Long duration;
    private double appointmentPoints;
    private StatusOfAppointment statusOfAppointment;

    public PharmacistReportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacistFirstName() {
        return pharmacistFirstName;
    }

    public void setPharmacistFirstName(String pharmacistFirstName) {
        this.pharmacistFirstName = pharmacistFirstName;
    }

    public String getPharmacistLastName() {
        return pharmacistLastName;
    }

    public void setPharmacistLastName(String pharmacistLastName) {
        this.pharmacistLastName = pharmacistLastName;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getPharmacistAppointmentStartTime() {
        return pharmacistAppointmentStartTime;
    }

    public void setPharmacistAppointmentStartTime(LocalDateTime pharmacistAppointmentStartTime) {
        this.pharmacistAppointmentStartTime = pharmacistAppointmentStartTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public double getAppointmentPoints() {
        return appointmentPoints;
    }

    public void setAppointmentPoints(double appointmentPoints) {
        this.appointmentPoints = appointmentPoints;
    }

    public StatusOfAppointment getStatusOfAppointment() {
        return statusOfAppointment;
    }

    public void setStatusOfAppointment(StatusOfAppointment statusOfAppointment) {
        this.statusOfAppointment = statusOfAppointment;
    }
}

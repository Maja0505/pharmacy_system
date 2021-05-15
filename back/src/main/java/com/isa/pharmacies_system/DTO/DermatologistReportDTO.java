package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;

import java.time.LocalDateTime;

public class DermatologistReportDTO {

    private Long id;
    private Long pharmacyId;
    private Long dermatologistId;
    private String pharmacyName;
    private String dermatologistFirstName;
    private String dermatologistLastName;
    private String reportInfo;
    private Long appointmentId;
    private LocalDateTime dermatologistAppointmentStartTime;
    private LocalDateTime dermatologistAppointmentEndTime;
    private double appointmentPoints;
    private StatusOfAppointment statusOfAppointment;

    public DermatologistReportDTO() {
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

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getDermatologistFirstName() {
        return dermatologistFirstName;
    }

    public void setDermatologistFirstName(String dermatologistFirstName) {
        this.dermatologistFirstName = dermatologistFirstName;
    }

    public String getDermatologistLastName() {
        return dermatologistLastName;
    }

    public void setDermatologistLastName(String dermatologistLastName) {
        this.dermatologistLastName = dermatologistLastName;
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

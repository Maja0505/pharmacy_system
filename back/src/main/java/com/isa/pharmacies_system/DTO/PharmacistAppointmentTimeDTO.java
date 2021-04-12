package com.isa.pharmacies_system.DTO;

import java.time.LocalDateTime;

public class PharmacistAppointmentTimeDTO {

    LocalDateTime startTime;
    double duration;

    public PharmacistAppointmentTimeDTO() {
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}

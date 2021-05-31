package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class WorkingHoursDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime workingStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime workingEndTime;
    private Long staffId;

    public WorkingHoursDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getWorkingStartTime() {
        return workingStartTime;
    }

    public void setWorkingStartTime(LocalDateTime workingStartTime) {
        this.workingStartTime = workingStartTime;
    }

    public LocalDateTime getWorkingEndTime() {
        return workingEndTime;
    }

    public void setWorkingEndTime(LocalDateTime workingEndTime) {
        this.workingEndTime = workingEndTime;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}

package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.medicine.StatusOfEPrescription;

import java.time.LocalDateTime;

public class EPrescriptionDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime localDateTime;
    private StatusOfEPrescription statusOfEPrescription;

    public EPrescriptionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public StatusOfEPrescription getStatusOfEPrescription() {
        return statusOfEPrescription;
    }

    public void setStatusOfEPrescription(StatusOfEPrescription statusOfEPrescription) {
        this.statusOfEPrescription = statusOfEPrescription;
    }
}

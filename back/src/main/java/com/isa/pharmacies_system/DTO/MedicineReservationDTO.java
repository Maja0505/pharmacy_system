package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicineReservationDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfTakingMedicine;


    public MedicineReservationDTO() {
    }

    public LocalDate getDateOfTakingMedicine() {
        return dateOfTakingMedicine;
    }

    public void setDateOfTakingMedicine(LocalDate dateOfTakingMedicine) {
        this.dateOfTakingMedicine = dateOfTakingMedicine;
    }
}

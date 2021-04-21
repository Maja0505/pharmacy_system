package com.isa.pharmacies_system.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicineReservationDTO {


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

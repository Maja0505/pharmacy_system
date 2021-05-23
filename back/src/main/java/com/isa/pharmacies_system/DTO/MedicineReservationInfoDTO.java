package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;

import java.time.LocalDate;

public class MedicineReservationInfoDTO {

    private Long reservationId;
    private Long medicineId;
    private Long pharmacyId;
    private String medicineName;
    private String pharmacyName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate takingDate;
    private StatusOfMedicineReservation statusOfMedicineReservation;

    public MedicineReservationInfoDTO() {
    }


    public Long getReservationId() {
        return reservationId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public LocalDate getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(LocalDate takingDate) {
        this.takingDate = takingDate;
    }

    public StatusOfMedicineReservation getStatusOfMedicineReservation() {
        return statusOfMedicineReservation;
    }

    public void setStatusOfMedicineReservation(StatusOfMedicineReservation statusOfMedicineReservation) {
        this.statusOfMedicineReservation = statusOfMedicineReservation;
    }
}

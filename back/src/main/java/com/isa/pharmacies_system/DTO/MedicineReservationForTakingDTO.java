package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.medicine.FormOfMedicine;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.medicine.TypeOfMedicine;

import java.time.LocalDate;

public class MedicineReservationForTakingDTO {

    private Long medicineReservationId;
    private String patientFirstName;
    private String patientLastName;
    private String patientPhoneNumber;
    private String patientEmail;
    private String medicineName;
    private String medicineCode;
    private TypeOfMedicine typeOfMedicine;
    private FormOfMedicine formOfMedicine;
    private String manufacturerOfMedicine;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfTakingMedicine;
    private StatusOfMedicineReservation statusOfMedicineReservation;


    public MedicineReservationForTakingDTO() {
    }

    public Long getMedicineReservationId() {
        return medicineReservationId;
    }

    public void setMedicineReservationId(Long medicineReservationId) {
        this.medicineReservationId = medicineReservationId;
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

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public LocalDate getDateOfTakingMedicine() {
        return dateOfTakingMedicine;
    }

    public void setDateOfTakingMedicine(LocalDate dateOfTakingMedicine) {
        this.dateOfTakingMedicine = dateOfTakingMedicine;
    }

    public StatusOfMedicineReservation getStatusOfMedicineReservation() {
        return statusOfMedicineReservation;
    }

    public void setStatusOfMedicineReservation(StatusOfMedicineReservation statusOfMedicineReservation) {
        this.statusOfMedicineReservation = statusOfMedicineReservation;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public TypeOfMedicine getTypeOfMedicine() {
        return typeOfMedicine;
    }

    public void setTypeOfMedicine(TypeOfMedicine typeOfMedicine) {
        this.typeOfMedicine = typeOfMedicine;
    }

    public FormOfMedicine getFormOfMedicine() {
        return formOfMedicine;
    }

    public void setFormOfMedicine(FormOfMedicine formOfMedicine) {
        this.formOfMedicine = formOfMedicine;
    }

    public String getManufacturerOfMedicine() {
        return manufacturerOfMedicine;
    }

    public void setManufacturerOfMedicine(String manufacturerOfMedicine) {
        this.manufacturerOfMedicine = manufacturerOfMedicine;
    }
}

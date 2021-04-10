package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.medicine.FormOfMedicine;
import com.isa.pharmacies_system.domain.medicine.PrescriptionRegime;
import com.isa.pharmacies_system.domain.medicine.TypeOfMedicine;

public class MedicineInfoDTO {

    private long id;
    private String medicineName;
    private String medicineCode;
    private TypeOfMedicine typeOfMedicine;
    private FormOfMedicine formOfMedicine;
    private String manufacturerOfMedicine;
    private PrescriptionRegime prescriptionRegime;
    private String notes;

    public MedicineInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public PrescriptionRegime getPrescriptionRegime() {
        return prescriptionRegime;
    }

    public void setPrescriptionRegime(PrescriptionRegime prescriptionRegime) {
        this.prescriptionRegime = prescriptionRegime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.user.CategoryOfPatient;

import java.util.List;

public class PatientAdditionalInfoDTO extends UserPersonalInfoDTO{

    private double patientPoints;
    private CategoryOfPatient categoryOfPatient;
    private List<MedicineForAllergiesDTO> medicineForAllergiesDTO;
    private int penalty;

    public PatientAdditionalInfoDTO() {
    }

    public double getPatientPoints() {
        return patientPoints;
    }

    public void setPatientPoints(double patientPoints) {
        this.patientPoints = patientPoints;
    }

    public CategoryOfPatient getCategoryOfPatient() {
        return categoryOfPatient;
    }

    public void setCategoryOfPatient(CategoryOfPatient categoryOfPatient) {
        this.categoryOfPatient = categoryOfPatient;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public List<MedicineForAllergiesDTO> getMedicineForAllergiesDTO() {
        return medicineForAllergiesDTO;
    }

    public void setMedicineForAllergiesDTO(List<MedicineForAllergiesDTO> medicineForAllergiesDTO) {
        this.medicineForAllergiesDTO = medicineForAllergiesDTO;
    }
}

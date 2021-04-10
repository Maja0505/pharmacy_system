package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.user.CategoryOfPatient;

public class PatientAdditionalInfoDTO {

    private double patientPoints;
    private CategoryOfPatient categoryOfPatient;

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
}

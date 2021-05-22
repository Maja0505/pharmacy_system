package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.rating.RatingScale;
import com.isa.pharmacies_system.domain.rating.TypeOfRating;
import com.isa.pharmacies_system.domain.user.Patient;

public class RatingDTO {
    private long id;
    private RatingScale grade;
    private Long patientId;
    private TypeOfRating typeOfRating;

    public RatingDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatingScale getGrade() {
        return grade;
    }

    public void setGrade(RatingScale grade) {
        this.grade = grade;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public TypeOfRating getTypeOfRating() {
        return typeOfRating;
    }

    public void setTypeOfRating(TypeOfRating typeOfRating) {
        this.typeOfRating = typeOfRating;
    }
}

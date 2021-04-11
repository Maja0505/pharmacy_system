package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.schedule.TypeOfVacation;

import java.time.LocalDate;

public class VacationRequestDTO {

    private LocalDate vacationStartDate;
    private LocalDate vacationEndDate;
    private String vacationRequestNotes;
    private TypeOfVacation typeOfVacation;
    private Long staffId;

    public VacationRequestDTO() {
    }

    public TypeOfVacation getTypeOfVacation() {
        return typeOfVacation;
    }

    public void setTypeOfVacation(TypeOfVacation typeOfVacation) {
        this.typeOfVacation = typeOfVacation;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDate getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(LocalDate vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public String getVacationRequestNotes() {
        return vacationRequestNotes;
    }

    public void setVacationRequestNotes(String vacationRequestNotes) {
        this.vacationRequestNotes = vacationRequestNotes;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}

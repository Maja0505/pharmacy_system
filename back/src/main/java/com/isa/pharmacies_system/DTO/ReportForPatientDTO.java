package com.isa.pharmacies_system.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;

public class ReportForPatientDTO {

    private Long appointmentId;
    private String reportInfo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;
    private Set<RecipeItemDTO> recipeItemsDTO;

    public ReportForPatientDTO() {
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<RecipeItemDTO> getRecipeItemsDTO() {
        return recipeItemsDTO;
    }

    public void setRecipeItemsDTO(Set<RecipeItemDTO> recipeItemsDTO) {
        this.recipeItemsDTO = recipeItemsDTO;
    }
}

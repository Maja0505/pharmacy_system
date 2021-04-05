package com.isa.pharmacies_system.domain.report;

import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table(name="dermatologist_reports")
@Inheritance(strategy=JOINED)
public class DermatologistReport {

    @Id
    @SequenceGenerator(name = "mySeqGenDermatologistReport", sequenceName = "mySeqDermatologistReport", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenDermatologistReport")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private DermatologistAppointment dermatologistAppointment;

    @Column(name = "reportInfos", unique = false, nullable = true)
    private String reportInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipeForDermatologistReport;

    public DermatologistReport(long id, DermatologistAppointment dermatologistAppointment, String reportInfo, Recipe recipeForDermatologistReport) {
        this.id = id;
        this.dermatologistAppointment = dermatologistAppointment;
        this.reportInfo = reportInfo;
        this.recipeForDermatologistReport = recipeForDermatologistReport;
    }

    public DermatologistReport() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DermatologistAppointment getDermatologistAppointment() {
        return dermatologistAppointment;
    }

    public void setDermatologistAppointment(DermatologistAppointment dermatologistAppointment) {
        this.dermatologistAppointment = dermatologistAppointment;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public Recipe getRecipeForDermatologistReport() {
        return recipeForDermatologistReport;
    }

    public void setRecipeForDermatologistReport(Recipe recipeForDermatologistReport) {
        this.recipeForDermatologistReport = recipeForDermatologistReport;
    }
}

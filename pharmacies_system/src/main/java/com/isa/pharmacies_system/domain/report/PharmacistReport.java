package com.isa.pharmacies_system.domain.report;


import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table(name="pharmacist_reports")
@Inheritance(strategy=JOINED)
public class PharmacistReport {

    @Id
    @SequenceGenerator(name = "mySeqGenPharmacistReport", sequenceName = "mySeqPharmacistReport", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenPharmacistReport")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private PharmacistAppointment pharmacistAppointment;

    @Column(name = "reportInfos", unique = false, nullable = true)
    private String reportInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipeForPharmacistReport;

    public PharmacistReport() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PharmacistAppointment getPharmacistAppointment() {
        return pharmacistAppointment;
    }

    public void setPharmacistAppointment(PharmacistAppointment pharmacistAppointment) {
        this.pharmacistAppointment = pharmacistAppointment;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public Recipe getRecipeForPharmacistReport() {
        return recipeForPharmacistReport;
    }

    public void setRecipeForPharmacistReport(Recipe recipeForReport) {
        this.recipeForPharmacistReport = recipeForReport;
    }

    public PharmacistReport(long id, PharmacistAppointment pharmacistAppointment, String reportInfo, Recipe recipeForPharmacistReport) {
        this.id = id;
        this.pharmacistAppointment = pharmacistAppointment;
        this.reportInfo = reportInfo;
        this.recipeForPharmacistReport = recipeForPharmacistReport;
    }
}

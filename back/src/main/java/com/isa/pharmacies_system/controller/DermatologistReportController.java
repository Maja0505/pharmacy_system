package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.ReportForPatientDTO;
import com.isa.pharmacies_system.converter.ReportConverter;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.service.iService.IDermatologistReportService;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/dermatologistReport")
public class DermatologistReportController {

    private IDermatologistReportService dermatologistReportService;
    private IMedicineService medicineService;
    private ReportConverter reportConverter;

    @Autowired
    public DermatologistReportController(IDermatologistReportService dermatologistReportService, IMedicineService medicineService) {
        this.dermatologistReportService = dermatologistReportService;
        this.medicineService = medicineService;
        this.reportConverter = new ReportConverter();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DermatologistReport>> findAll(){
        return new ResponseEntity<>(dermatologistReportService.findAll(), HttpStatus.OK);
    }

    //Nemanja
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Boolean> createDermatologistReport(@RequestBody ReportForPatientDTO reportForPatientDTO){
        try {
            DermatologistReport dermatologistReport = reportConverter.convertReportDTOToDermatologistReport(reportForPatientDTO,medicineService);
            dermatologistReportService.createDermatologistReport(dermatologistReport,reportForPatientDTO.getAppointmentId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.converter.PharmacistReportConverter;
import com.isa.pharmacies_system.service.iService.IDermatologistReportService;
import com.isa.pharmacies_system.service.iService.IPharmacistReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/pharmacistReport")
public class PharmacistReportController {

    private IPharmacistReportService pharmacistReportService;
    private PharmacistReportConverter pharmacistReportConverter;

    @Autowired
    public PharmacistReportController(IDermatologistReportService dermatologistReportService, IPharmacistReportService pharmacistReportService) {
        this.pharmacistReportService = pharmacistReportService;
        this.pharmacistReportConverter = new PharmacistReportConverter();
    }

    //#1
    @GetMapping("/all/patient/{idPatient}/{page}")
    public ResponseEntity<List<PharmacistReportDTO>> findAllForPatient(@PathVariable Long idPatient, @PathVariable int page){
        try{
            List<PharmacistReportDTO> pharmacistReportDTOS = pharmacistReportConverter.convertPharmacistReportToPharmacistReportDTOS(pharmacistReportService.findAllPharmacistReportForPatient(idPatient,page));
            return new ResponseEntity<>(pharmacistReportDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.WorkingHoursDTO;
import com.isa.pharmacies_system.converter.WorkingHoursConverter;
import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import com.isa.pharmacies_system.service.iService.IWorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/workingHours")
public class WorkingHoursController {

    private IWorkingHoursService workingHoursService;
    private WorkingHoursConverter workingHoursConverter;

    @Autowired
    public WorkingHoursController(IWorkingHoursService workingHoursService) {
        this.workingHoursService = workingHoursService;
        this.workingHoursConverter = new WorkingHoursConverter();
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/all/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<WorkingHoursDTO>> getAllFutureWorkingHoursByDermatologistInPharmacy(@PathVariable Long dermatologistId, @PathVariable Long pharmacyId){
        try {
            List<WorkingHours> workingHoursList = workingHoursService.getAllFutureWorkingHoursForDermatologistInPharmacy(dermatologistId,pharmacyId);
            return new ResponseEntity<>(workingHoursConverter.convertWorkingHoursListToWorkingHoursDTOList(workingHoursList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/allPharmacistWorkingHours/{pharmacistId}")
    public ResponseEntity<List<WorkingHoursDTO>> getAllFutureWorkingHoursByPharmacist(@PathVariable Long pharmacistId){
        try {
            List<WorkingHours> workingHoursList = workingHoursService.getAllFutureWorkingHoursForPharmacist(pharmacistId);
            return new ResponseEntity<>(workingHoursConverter.convertWorkingHoursListToWorkingHoursDTOList(workingHoursList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}

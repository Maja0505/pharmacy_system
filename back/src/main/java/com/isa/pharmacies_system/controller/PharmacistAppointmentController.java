package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/pharmacistAppointment")
public class PharmacistAppointmentController {

    private IPharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    public PharmacistAppointmentController(IPharmacistAppointmentService pharmacistAppointmentService) {
        this.pharmacistAppointmentService = pharmacistAppointmentService;
    }

    @GetMapping("/allPastAppointment/{pharmacistId}")
    public ResponseEntity<List<PharmacistAppointment>> getAllPastPharmacistAppointment(@PathVariable("pharmacistId") Long id){
        try {
            return new ResponseEntity<>(pharmacistAppointmentService.getAllPastPharmacistAppointmentByPharmacist(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}

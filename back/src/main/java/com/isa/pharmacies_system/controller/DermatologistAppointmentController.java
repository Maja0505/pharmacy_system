package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.converter.DermatologistAppointmentConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/dermatologistAppointment")
public class DermatologistAppointmentController {

    private IDermatologistAppointmentService dermatologistAppointmentService;
    private DermatologistAppointmentConverter dermatologistAppointmentConverter;

    public DermatologistAppointmentController(IDermatologistAppointmentService dermatologistAppointmentService) {

        this.dermatologistAppointmentService = dermatologistAppointmentService;
        this.dermatologistAppointmentConverter = new DermatologistAppointmentConverter();
    }

    @GetMapping("/all/open")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getOpenDermatologistAppointment(){

        try{
            List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getOpenDermatologistAppointment();
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointments), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{patientId}", consumes = "application/json")
    public ResponseEntity<Boolean> bookDermatologistAppointment(@PathVariable Long patientId, @RequestBody DermatologistAppointmentDTO dermatologistAppointmentDTO){

        try{
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(dermatologistAppointmentDTO.getId());
            dermatologistAppointmentService.bookDermatologistAppointment(patientId,dermatologistAppointment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

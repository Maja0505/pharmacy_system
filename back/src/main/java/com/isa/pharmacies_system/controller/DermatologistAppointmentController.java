package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.service.EmailService;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.converter.DermatologistAppointmentConverter;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("api/dermatologistAppointment")
public class DermatologistAppointmentController {

    private IDermatologistAppointmentService dermatologistAppointmentService;
    private PatientConverter patientConverter;
    private DermatologistAppointmentConverter dermatologistAppointmentConverter;
    private EmailService emailService;

    @Autowired
    public DermatologistAppointmentController(IDermatologistAppointmentService dermatologistAppointmentService, EmailService emailService) {
        this.dermatologistAppointmentService = dermatologistAppointmentService;
        this.emailService = emailService;
        this.patientConverter = new PatientConverter();
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

    @PutMapping(value = "/book/{appointmentId}/{patientId}")
    public ResponseEntity<Boolean> bookDermatologistAppointment(@PathVariable Long patientId, @PathVariable Long appointmentId){
        try{
            if(dermatologistAppointmentService.bookDermatologistAppointment(patientId,appointmentId)){
                emailService.sendNotificationForSuccessBookAppointment(patientId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/cancel")
    public ResponseEntity<Boolean> cancelDermatologistAppointment(@RequestBody DermatologistAppointmentDTO dermatologistAppointmentDTO){
        try {
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(dermatologistAppointmentDTO.getId());
            if(dermatologistAppointmentService.cancelDermatologistAppointment(dermatologistAppointment)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //Nemanja
    @GetMapping("/allPastAppointmentByDermatologist/{dermatologistId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologist(@PathVariable ("dermatologistId") Long id,@PathVariable int page){
        try {
            Page<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologist(id,page);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //Nemanja
    @GetMapping("/allPastAppointmentByDermatologistAndPharmacy/{dermatologistId}/{pharmacyId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId,@PathVariable int page){
        try {
            Page<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId,page);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @GetMapping(value = "/sortByAppointmentEndTime/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastDermatologistAppointmentByAppointmentEndTime(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,@PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(dermatologistAppointmentService.sortByAppointmentEndTime(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(dermatologistAppointmentService.sortByAppointmentEndTime(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @GetMapping("/allFutureOpen/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllFutureOpenDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.getAllFutureOpenDermatologistAppointmentForDermatologistInPharmacy(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}

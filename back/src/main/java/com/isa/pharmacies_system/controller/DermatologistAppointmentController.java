package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.EmailService;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import com.isa.pharmacies_system.service.iService.IPatientService;
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

    @Autowired
    private EmailService emailService;

    private IPatientService patientService;

    @Autowired
    public DermatologistAppointmentController(IDermatologistAppointmentService dermatologistAppointmentService, IPatientService patientService) {
        this.dermatologistAppointmentService = dermatologistAppointmentService;
        this.patientConverter = new PatientConverter();
        this.dermatologistAppointmentConverter = new DermatologistAppointmentConverter();
        this.patientService = patientService;



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

    @PutMapping(value = "/book/{patientId}", consumes = "application/json")
    public ResponseEntity<Boolean> bookDermatologistAppointment(@PathVariable Long patientId, @RequestBody DermatologistAppointmentDTO dermatologistAppointmentDTO){

        try{
            Patient patient = patientService.findOne(patientId);
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(dermatologistAppointmentDTO.getId());
            dermatologistAppointmentService.bookDermatologistAppointment(patient,dermatologistAppointment);
            emailService.sendNotificationForSuccessBookAppointment(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
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



    @GetMapping("/allPastAppointmentByDermatologist/{dermatologistId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologist(@PathVariable ("dermatologistId") Long id,@PathVariable int page){
        try {
            Page<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologist(id,page);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/allPastAppointmentByDermatologistAndPharmacy/{dermatologistId}/{pharmacyId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId,@PathVariable int page){
        try {
            Page<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId,page);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

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

}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.converter.PharmacistAppointmentConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.service.EmailService;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/pharmacistAppointment")
@CrossOrigin(origins="http://localhost:3000")
public class PharmacistAppointmentController {

    private IPharmacistAppointmentService pharmacistAppointmentService;
    private PatientConverter patientConverter;
    private EmailService emailService;
    private PharmacistAppointmentConverter pharmacistAppointmentConverter;

    @Autowired
    public PharmacistAppointmentController(IPharmacistAppointmentService pharmacistAppointmentService, EmailService emailService) {
        this.pharmacistAppointmentService = pharmacistAppointmentService;
        this.emailService = emailService;
        this.patientConverter = new PatientConverter();
        this.pharmacistAppointmentConverter = new PharmacistAppointmentConverter();
    }


    //#1[3.16]Korak3
    @PostMapping(value = "/book/{pharmacistId}/{patientId}", consumes = "application/json")
    public ResponseEntity<Boolean> bookPharmacistAppointment(@PathVariable Long patientId,@PathVariable Long pharmacistId,@RequestBody PharmacistAppointmentTimeDTO timeDTO){

        try {
            if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 10){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(pharmacistAppointmentService.bookPharmacistAppointment(patientId,pharmacistId,timeDTO)){
                emailService.sendNotificationForSuccessBookAppointment(patientId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1[3.18]
    @GetMapping(value = "/all/reserved/{patientId}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllFutureReservedPharmacistAppointmentForPatient(@PathVariable Long patientId){
        try {
           List<PharmacistAppointment> pharmacistAppointments =  pharmacistAppointmentService.getFutureReservedAppointment(patientId);
           List<PharmacistAppointmentDTO> pharmacistAppointmentDTOS =pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointments);
           return new ResponseEntity<>(pharmacistAppointmentDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //#1[3.18]
    @PutMapping(value = "/cancel/{appointmentId}")
    public ResponseEntity<Boolean> cancelPharmacistAppointment(@PathVariable Long appointmentId){
        try {
            return new ResponseEntity<>(pharmacistAppointmentService.cancelPharmacistAppointment(appointmentId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @GetMapping("/allPastAppointment/{pharmacistId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastPharmacistAppointment(@PathVariable("pharmacistId") Long id,@PathVariable int page){
        try {
            Page<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllPastPharmacistAppointmentByPharmacist(id,page);
            return new ResponseEntity<>(patientConverter.convertPatientPharmacistAppointmentInfoToDTO(pharmacistAppointmentList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PutMapping(value = "/sortByAppointmentDuration/{asc}",consumes = "application/json")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getSortedPastPharmacistAppointmentByAppointmentDuration(@RequestBody List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(pharmacistAppointmentService.sortByAppointmentDuration(patientAppointmentInfoDTOList,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(pharmacistAppointmentService.sortByAppointmentDuration(patientAppointmentInfoDTOList,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    //Nemanja
    @GetMapping("/allMissed/{pharmacistId}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllMissedPharmacistAppointmentByPharmacist(@PathVariable Long pharmacistId){
        try {
            List<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllMissedPharmacistAppointmentByPharmacist(pharmacistId);
            return new ResponseEntity<>(pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @GetMapping("/allExpired/{pharmacistId}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllExpiredPharmacistAppointmentByPharmacist(@PathVariable Long pharmacistId){
        try {
            List<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllExpiredPharmacistAppointmentByPharmacist(pharmacistId);
            return new ResponseEntity<>(pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @GetMapping("/allReserved/{pharmacistId}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllReservedPharmacistAppointmentByPharmacist(@PathVariable Long pharmacistId){
        try {
            List<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllReservedPharmacistAppointmentByPharmacist(pharmacistId);
            return new ResponseEntity<>(pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PutMapping("/changeStatusToMissed/{id}")
    public ResponseEntity<Boolean> changePharmacistAppointmentStatusToMissed(@PathVariable Long id){
        try {
            PharmacistAppointment pharmacistAppointment = pharmacistAppointmentService.findOne(id);
            if(pharmacistAppointmentService.changePharmacistAppointmentStatusToMissed(pharmacistAppointment)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

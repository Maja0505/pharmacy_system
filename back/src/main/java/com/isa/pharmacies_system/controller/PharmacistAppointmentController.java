package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.*;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/pharmacistAppointment")
public class PharmacistAppointmentController {

    private IPharmacistAppointmentService pharmacistAppointmentService;
    private PatientConverter patientConverter;
    private EmailService emailService;
    private PharmacistAppointmentConverter pharmacistAppointmentConverter;
    private IPriceListService priceListService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PharmacistAppointmentController(IPharmacistAppointmentService pharmacistAppointmentService, EmailService emailService, IPriceListService priceListService,PasswordEncoder passwordEncoder) {
        this.pharmacistAppointmentService = pharmacistAppointmentService;
        this.emailService = emailService;
        this.priceListService = priceListService;
        this.passwordEncoder = passwordEncoder;
        this.patientConverter = new PatientConverter(passwordEncoder);
        this.pharmacistAppointmentConverter = new PharmacistAppointmentConverter(priceListService);
    }


    //#1[3.16]Korak3
    @PostMapping(value = "/book/{pharmacistId}/{patientId}", consumes = "application/json")
    public ResponseEntity<Boolean> bookPharmacistAppointment(@PathVariable Long patientId,@PathVariable Long pharmacistId,@RequestBody PharmacistAppointmentTimeDTO timeDTO){

        try {
            if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 5){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(pharmacistAppointmentService.bookPharmacistAppointment(patientId,pharmacistId,timeDTO,true)){
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
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
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
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
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
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
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
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
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
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
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

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/allFutureReserved/{pharmacistId}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllFutureReservedAppointmentByPharmacist(@PathVariable Long pharmacistId){
        try {
            List<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllFutureReservedAppointmentByPharmacist(pharmacistId);
            return new ResponseEntity<>(pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PostMapping(value = "/bookByPharmacist",consumes = "application/json")
    public ResponseEntity<Boolean> bookPharmacistAppointmentByPharmacist(@RequestBody AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO){
        try {
            PharmacistAppointmentTimeDTO pharmacistAppointmentTimeDTO = pharmacistAppointmentConverter.convertAppointmentScheduleByStaffDTOToPharmacistAppointmentTimeDTO(appointmentScheduleByStaffDTO);
            if(pharmacistAppointmentService.bookPharmacistAppointment(appointmentScheduleByStaffDTO.getPatientId(), appointmentScheduleByStaffDTO.getStaffId(),pharmacistAppointmentTimeDTO,false)){
                emailService.sendNotificationForSuccessBookAppointment(appointmentScheduleByStaffDTO.getPatientId());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/searchAllFutureReservedByPatient/{pharmacistId}/{firstName}/{lastName}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> searchFutureReservedAppointmentsByPatientFirstAndLastName(@PathVariable Long pharmacistId, @PathVariable String firstName,@PathVariable String lastName){
        try {
            List<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.searchAllFutureReservedByPatientFirstAndLastName(pharmacistId,firstName,lastName);
            if(!pharmacistAppointmentList.isEmpty()){
                List<PharmacistAppointmentDTO> pharmacistAppointmentDTOList = pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(pharmacistAppointmentList);
                return new ResponseEntity<>(pharmacistAppointmentDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}

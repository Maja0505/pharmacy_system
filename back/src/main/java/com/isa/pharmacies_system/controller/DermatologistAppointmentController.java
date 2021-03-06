package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.service.EmailService;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@CrossOrigin(origins="*")
@RequestMapping("api/dermatologistAppointment")
public class DermatologistAppointmentController {

    private IDermatologistAppointmentService dermatologistAppointmentService;
    private PatientConverter patientConverter;
    private DermatologistAppointmentConverter dermatologistAppointmentConverter;
    private EmailService emailService;
    private IPriceListService priceListService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public DermatologistAppointmentController(IDermatologistAppointmentService dermatologistAppointmentService, EmailService emailService, IPriceListService priceListService) {
        this.dermatologistAppointmentService = dermatologistAppointmentService;
        this.emailService = emailService;
        this.priceListService = priceListService;
        this.patientConverter = new PatientConverter(new BCryptPasswordEncoder());
        this.dermatologistAppointmentConverter = new DermatologistAppointmentConverter(priceListService);
    }

    //#1[3.13]
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/all/open/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getOpenDermatologistAppointment(@PathVariable Long pharmacyId){
        try{
            List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getOpenDermatologistAppointment(pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointments), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT') or hasRole('ROLE_DERMATOLOGIST')")
    @PutMapping(value = "/book/{appointmentId}/{patientId}")
    public ResponseEntity<Boolean> bookDermatologistAppointment(@PathVariable Long patientId, @PathVariable Long appointmentId){
        try{
            if(dermatologistAppointmentService.bookDermatologistAppointment(patientId,appointmentId)){
                emailService.sendNotificationForSuccessBookAppointment(patientId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
                return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1[3.15]
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/cancel")
    public ResponseEntity<Boolean> cancelDermatologistAppointment(@RequestBody DermatologistAppointmentDTO dermatologistAppointmentDTO){
        try {
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(dermatologistAppointmentDTO.getId());
                return new ResponseEntity<>(dermatologistAppointmentService.cancelDermatologistAppointment(dermatologistAppointment),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allPastAppointmentByDermatologist/{dermatologistId}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologist(@PathVariable ("dermatologistId") Long id,@PathVariable int page){
        try {
            List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologist(id);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allPastAppointmentByDermatologistAndPharmacy/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointments = dermatologistAppointmentService.getAllPastDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
            return new ResponseEntity<>(patientConverter.convertPatientDermatologistAppointmentInfoToDTO(dermatologistAppointments),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @PutMapping(value = "/sortByAppointmentEndTime/{asc}",consumes = "application/json")
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
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allFutureOpen/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllFutureOpenDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.getAllFutureOpenDermatologistAppointmentForDermatologistInPharmacy(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allFutureReserved/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllFutureReservedDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allMissed/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllMissedDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.getAllMissedDermatologistAppointmentByDermatologistAndPharmacyId(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allExpired/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllExpiredDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.getAllExpiredDermatologistAppointmentByDermatologistAndPharmacyId(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allReserved/{dermatologistId}/{pharmacyId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllReservedDermatologistAppointmentByDermatologistInPharmacy(@PathVariable Long dermatologistId,@PathVariable Long pharmacyId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.getAllReservedDermatologistAppointmentByDermatologistAndPharmacyId(dermatologistId,pharmacyId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @PostMapping(value = "/bookByDermatologist",consumes = "application/json")
    public ResponseEntity<Boolean> bookDermatologistAppointmentByDermatologist(@RequestBody AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO){
        try {
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentConverter.convertAppointmentScheduleByStaffDTOToDermatologistAppointment(appointmentScheduleByStaffDTO);
            if(dermatologistAppointmentService.bookDermatologistAppointmentByDermatologist(appointmentScheduleByStaffDTO,dermatologistAppointment)){
                emailService.sendNotificationForSuccessBookAppointment(appointmentScheduleByStaffDTO.getPatientId());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e ){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @PutMapping("/changeStatusToMissed/{id}")
    public ResponseEntity<Boolean> changeDermatologistAppointmentStatusToMissed(@PathVariable Long id){
        try {
            DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(id);
            if(dermatologistAppointmentService.changeDermatologistAppointmentStatusToMissed(dermatologistAppointment)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/searchAllFutureReservedByPatient/{dermatologistId}/{firstName}/{lastName}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> searchFutureReservedAppointmentsByPatientFirstAndLastName(@PathVariable Long dermatologistId, @PathVariable String firstName,@PathVariable String lastName){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.searchAllFutureReservedByPatientFirstAndLastName(dermatologistId,firstName,lastName);
            if(!dermatologistAppointmentList.isEmpty()){
                List<DermatologistAppointmentDTO> dermatologistAppointmentDTOList = dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList);
                return new ResponseEntity<>(dermatologistAppointmentDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/allFutureReserveByDermatologist/{dermatologistId}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllFutureReservedDermatologistAppointmentByDermatologist(@PathVariable Long dermatologistId){
        try {
            List<DermatologistAppointment> dermatologistAppointmentList = dermatologistAppointmentService.findAllFutureReservedDermatologistAppointmentByDermatologist(dermatologistId);
            return new ResponseEntity<>(dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(dermatologistAppointmentList),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}

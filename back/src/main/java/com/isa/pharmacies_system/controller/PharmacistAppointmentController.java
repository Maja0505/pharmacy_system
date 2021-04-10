package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/pharmacistAppointment")
public class PharmacistAppointmentController {

    private IPharmacistAppointmentService pharmacistAppointmentService;
    private PatientConverter patientConverter;

    @Autowired
    public PharmacistAppointmentController(IPharmacistAppointmentService pharmacistAppointmentService) {
        this.pharmacistAppointmentService = pharmacistAppointmentService;
        this.patientConverter = new PatientConverter();
    }

    @GetMapping("/allPastAppointment/{pharmacistId}/{page}")
    public ResponseEntity<List<PatientAppointmentInfoDTO>> getAllPastPharmacistAppointment(@PathVariable("pharmacistId") Long id,@PathVariable int page){
        try {
            Page<PharmacistAppointment> pharmacistAppointmentList = pharmacistAppointmentService.getAllPastPharmacistAppointmentByPharmacist(id,page);
            return new ResponseEntity<>(patientConverter.convertPatientPharmacistAppointmentInfoToDTO(pharmacistAppointmentList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/sortByAppointmentDuration/{asc}",consumes = "application/json")
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


}

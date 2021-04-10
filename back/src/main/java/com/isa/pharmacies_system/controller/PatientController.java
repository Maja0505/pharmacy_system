package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PatientPasswordDTO;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.DTO.PatientInfoDTO;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {


    private IPatientService patientService;
    private PatientConverter patientConverter;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
        this.patientConverter = new PatientConverter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id){
        Patient patient = patientService.findOne(id);
        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    public ResponseEntity<List<Patient>> getAllPatient(){
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value ="/update", consumes = "application/json")
    public ResponseEntity<Boolean> updatePatientProfileInfo(@RequestBody  PatientInfoDTO patientInfoDTO){
        try{
            Patient patient = patientService.findOne(patientInfoDTO.getId());
            patientService.savePatient(patientConverter.convertDTOToPatientPersonalInfo(patientInfoDTO,patient));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changePassword(@RequestBody PatientPasswordDTO patientPasswordDTO){

        try {
            if(patientService.changePassword(patientPasswordDTO)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}

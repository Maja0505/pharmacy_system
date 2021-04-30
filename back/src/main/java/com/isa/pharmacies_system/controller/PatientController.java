package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.converter.DermatologistAppointmentConverter;
import com.isa.pharmacies_system.converter.PatientConverter;
import com.isa.pharmacies_system.converter.UserConverter;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.DermatologistAppointmentService;
import com.isa.pharmacies_system.service.MedicineService;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "/api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {


    private IPatientService patientService;
    private UserConverter userConverter;
    private PatientConverter patientConverter;
    private DermatologistAppointmentService dermatologistAppointmentService;
    private DermatologistAppointmentConverter dermatologistAppointmentConverter;
    private IMedicineService medicineService;

    @Autowired
    public PatientController(IPatientService patientService, DermatologistAppointmentService dermatologistAppointmentService, MedicineService medicineService) {

        this.patientService = patientService;
        this.userConverter = new UserConverter();
        this.patientConverter = new PatientConverter();
        this.dermatologistAppointmentConverter = new DermatologistAppointmentConverter();
        this.dermatologistAppointmentService = dermatologistAppointmentService;
        this.medicineService = medicineService;

    }

    //#1
    @GetMapping(value = "/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id){

        Patient patient = patientService.findOne(id);
        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    //#1
    @GetMapping(value = "/all")
    public ResponseEntity<List<Patient>> getAllPatient(){
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    //#1
    @GetMapping(value = "/{id}/profileInfo")
    public ResponseEntity<UserPersonalInfoDTO> getPatientProfileInfo(@PathVariable Long id){

        try{
            Patient patient = patientService.findOne(id);
            return new ResponseEntity<>(userConverter.convertPatientPersonalInfoToDTO(patient),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PutMapping(value ="/update", consumes = "application/json")
    public ResponseEntity<Boolean> updatePatientProfileInfo(@RequestBody UserPersonalInfoDTO userPersonalInfoDTO){

        try{
            Patient patient = patientService.findOne(userPersonalInfoDTO.getId());
            if(patient.getEmail().equals(userPersonalInfoDTO.getEmail())){
                patientService.savePatient(userConverter.convertDTOToPatientPersonalInfo(userPersonalInfoDTO,patient));
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserPasswordDTO userPasswordDTO){

        try {
            if(patientService.changePassword(userPasswordDTO)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @GetMapping(value = "/{id}/additionalInfo")
    public ResponseEntity<PatientAdditionalInfoDTO> getPatientAdditionalInfo(@PathVariable Long id){

        try{
            Patient patient = patientService.findOne(id);
            return new ResponseEntity<>(patientConverter.convertPatientAdditionalInfoToDTO(patient),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PutMapping(value = "/{patientId}/addMedicineAllergies/{medicineId}")
    public ResponseEntity<Boolean> addMedicineAllergies(@PathVariable Long patientId, @PathVariable Long medicineId){

        try {
            Medicine medicine = medicineService.findOne(medicineId);
            Patient patient = patientService.findOne(patientId);
            patientService.addMedicineAllergies(patient,medicine);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //#1
    @GetMapping(value = "/{id}/dermatologistAppointment", consumes = "application/json")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getDermatologistAppointmentsForPatient(@PathVariable Long id){

        try {
            Set<DermatologistAppointment> dermatologistAppointments = patientService.getDermatologistAppointmentForPatient(id);
            List<DermatologistAppointmentDTO> dermatologistAppointmentDTOS = dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(List.copyOf(dermatologistAppointments));
            return new ResponseEntity<>(dermatologistAppointmentDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

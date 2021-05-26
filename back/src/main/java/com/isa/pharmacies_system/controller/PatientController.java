package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.converter.*;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.DermatologistAppointmentService;
import com.isa.pharmacies_system.service.MedicineService;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private DermatologistAppointmentConverter dermatologistAppointmentConverter;
    private PharmacistAppointmentConverter pharmacistAppointmentConverter;
    private IMedicineService medicineService;
    private PasswordEncoder passwordEncoder;
    private IPriceListService priceListService;
    private MedicineReservationConverter medicineReservationConverter;
    private PharmacyConverter pharmacyConverter;
    private EPrescriptionItemConverter ePrescriptionItemConverter;


    

    @Autowired
    public PatientController(IPatientService patientService, DermatologistAppointmentService dermatologistAppointmentService, MedicineService medicineService, IPriceListService priceListService, PasswordEncoder passwordEncoder) {

        this.patientService = patientService;
        this.priceListService = priceListService;
        this.userConverter = new UserConverter();
        this.passwordEncoder = passwordEncoder;
        this.patientConverter = new PatientConverter(passwordEncoder);
        this.dermatologistAppointmentConverter = new DermatologistAppointmentConverter(priceListService);
        this.pharmacistAppointmentConverter = new PharmacistAppointmentConverter(priceListService);
        this.medicineService = medicineService;
        this.medicineReservationConverter = new MedicineReservationConverter();
        this.pharmacyConverter = new PharmacyConverter(priceListService);
        this.ePrescriptionItemConverter = new EPrescriptionItemConverter();

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
    @PreAuthorize("hasRole('ROLE_PATIENT')")
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
    @PreAuthorize("hasRole('ROLE_PATIENT')")
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
    @PreAuthorize("hasRole('ROLE_PATIENT')")
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
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/{patientId}/addMedicineAllergies/{medicineId}")
    public ResponseEntity<Boolean> addMedicineAllergies(@PathVariable Long patientId, @PathVariable Long medicineId){

        try {
            return new ResponseEntity<>(patientService.addMedicineAllergies(patientId,medicineId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/{patientId}/removeMedicineAllergies/{medicineId}", consumes = "application/json")
    public ResponseEntity<Boolean> removeMedicineAllergies(@PathVariable Long patientId, @PathVariable Long medicineId){

        try {
            return new ResponseEntity<>(patientService.removeMedicineAllergies(patientId,medicineId),HttpStatus.OK);
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

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/dermatologistAppointment/all/reserved/{page}")
    public ResponseEntity<List<DermatologistAppointmentDTO>> getAllReservedDermatologistAppointmentsForPatient(@PathVariable Long id,@PathVariable int page){

        try {
            List<DermatologistAppointmentDTO> dermatologistAppointmentDTOS = dermatologistAppointmentConverter.convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(List.copyOf(patientService.getAllReservedDermatologistAppointmentsForPatient(id,page)));
            return new ResponseEntity<>(dermatologistAppointmentDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/pharmacistAppointment/all/reserved/{page}")
    public ResponseEntity<List<PharmacistAppointmentDTO>> getAllReservedPharmacistAppointmentsForPatient(@PathVariable Long id,@PathVariable int page){

        try {
            List<PharmacistAppointmentDTO> pharmacistAppointmentDTOS = pharmacistAppointmentConverter.convertPharmacistAppointmentsListToDTOS(List.copyOf(patientService.getAllReservedPharmacistAppointmentsForPatient(id,page)));
            return new ResponseEntity<>(pharmacistAppointmentDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/medicineReservation/{page}")
    public ResponseEntity<List<MedicineReservationInfoDTO>> getAllMedicineReservationsForPatient(@PathVariable Long id, @PathVariable int page){

        try {
            Page<MedicineReservation> medicineReservations = patientService.getAllMedicineReservationsForPatient(id,page);
            return new ResponseEntity<>(medicineReservationConverter.convertMedicineReservationListToMedicineReservationInfoDTOS(medicineReservations),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/ePrescription")
    public ResponseEntity<List<EPrescriptionDTO>> getAllEPrescriptionsForPatient(@PathVariable Long id){

        try {
            return new ResponseEntity<>(ePrescriptionItemConverter.convertEPrescriptionToEPrescriptionDTOS(patientService.getAllEPrescriptionsForPatient(id)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/subscription")
    public ResponseEntity<List<PharmacyDTO>> getSubscriptionPharmaciesForPatient(@PathVariable Long id){

        try {
            List<PharmacyDTO> pharmacyDTOS = pharmacyConverter.convertPharmacyListToPharmacyDTOList(patientService.getSubscriptionPharmaciesForPatient(id));
            return new ResponseEntity<>(pharmacyDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}/dermatologist/expired")
    public ResponseEntity<List<UserPersonalInfoDTO>> getAllDermatologistForPatient(@PathVariable Long id){
        try {
            return new ResponseEntity<>(patientService.getAllDermatologistForPatient(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}/pharmacist/expired")
    public ResponseEntity<List<UserPersonalInfoDTO>> getAllPharmacistForPatient(@PathVariable Long id){
        try {
            return new ResponseEntity<>(patientService.getAllPharmacistForPatient(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}/medicine")
    public ResponseEntity<List<MedicineDTO>> getAllMedicinesForPatient(@PathVariable Long id){
        try {
            return new ResponseEntity<>(patientService.getAllMedicinesForPatient(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    //#1
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<List<PharmacyDTO>> getAllPharmaciesForPatient(@PathVariable Long id){
        try {
            return new ResponseEntity<>(patientService.getAllPharmaciesForPatient(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }


}

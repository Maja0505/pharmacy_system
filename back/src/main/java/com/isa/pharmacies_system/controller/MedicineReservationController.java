package com.isa.pharmacies_system.controller;


import com.isa.pharmacies_system.DTO.MedicineReservationDTO;
import com.isa.pharmacies_system.DTO.MedicineReservationForTakingDTO;
import com.isa.pharmacies_system.DTO.MedicineReservationInfoDTO;
import com.isa.pharmacies_system.converter.MedicineReservationConverter;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.EmailService;
import com.isa.pharmacies_system.service.iService.IMedicineReservationService;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import com.isa.pharmacies_system.service.iService.IPharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "api/medicineReservation")
public class MedicineReservationController {

    private IMedicineReservationService medicineReservationService;
    private MedicineReservationConverter medicineReservationConverter;
    private IPatientService patientService;
    private IMedicineService medicineService;
    private IPharmacyService pharmacyService;
    private EmailService emailService;

    public MedicineReservationController(IMedicineReservationService medicineReservationService, IPatientService patientService, IMedicineService medicineService, IPharmacyService pharmacyService, EmailService emailService) {
        this.medicineReservationService = medicineReservationService;
        this.patientService = patientService;
        this.medicineService = medicineService;
        this.pharmacyService = pharmacyService;
        this.emailService = emailService;
        this.medicineReservationConverter = new MedicineReservationConverter();
    }

    //#1[3.19]
    @PostMapping(value = "/create/{patientId}/{medicineId}/{pharmacyId}", consumes = "application/json")
    public ResponseEntity<Boolean> createMedicineReservation(@PathVariable Long patientId, @PathVariable Long medicineId, @PathVariable Long pharmacyId, @RequestBody MedicineReservationDTO medicineReservationDTO){
        try {
            if(medicineReservationDTO.getDateOfTakingMedicine().isAfter(LocalDate.now()) || (medicineReservationDTO.getDateOfTakingMedicine().isEqual(LocalDate.now()) && LocalDateTime.now().isBefore(medicineReservationDTO.getDateOfTakingMedicine().atTime(20,00)))){
                Patient patient = patientService.findOne(patientId);
                Medicine medicine = medicineService.findOne(medicineId);
                Pharmacy pharmacy = pharmacyService.getById(pharmacyId);
                MedicineReservation medicineReservation = medicineReservationConverter.convertMedicineReservationDTOToMedicineReservarvation(medicineReservationDTO,patient,medicine,pharmacy);
                if(medicineReservationService.createMedicineReservation(medicineReservation)){
                    emailService.sendNotificationForSuccessMedicineReservation(medicineReservation);
                    return new ResponseEntity<>(true,HttpStatus.OK);
                }
                return new ResponseEntity<>(false,HttpStatus.OK);
            }
            return new ResponseEntity<>(false,HttpStatus.OK);
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //#1[3.19]
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/cancel", consumes = "application/json")
    public ResponseEntity<Boolean> createMedicineReservation(@RequestBody MedicineReservationInfoDTO medicineReservationInfoDTO){
        try {
          if(medicineReservationService.cancelMedicineReservation(medicineReservationInfoDTO)){
              return new ResponseEntity<>(true,HttpStatus.OK);
          }
            return new ResponseEntity<>(false,HttpStatus.OK);

        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/get/{medicineReservationId}/{pharmacyId}")
    public ResponseEntity<MedicineReservationForTakingDTO> getMedicineReservationByIdAndPharmacy(@PathVariable Long medicineReservationId,@PathVariable Long pharmacyId){
        try {
            MedicineReservation medicineReservation = medicineReservationService.getMedicineReservationInPharmacy(medicineReservationId,pharmacyId);
            if(medicineReservation != null){
                MedicineReservationForTakingDTO medicineReservationForTakingDTO = medicineReservationConverter.convertMedicineReservationToMedicineReservationForTakingDTO(medicineReservation);
                return new ResponseEntity<>(medicineReservationForTakingDTO,HttpStatus.OK);
            }
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PutMapping("/finish/{medicineReservationId}")
    public ResponseEntity<Boolean> finishMedicineReservation(@PathVariable Long medicineReservationId){
        try {
            MedicineReservation medicineReservation = medicineReservationService.getMedicineReservationById(medicineReservationId);
            if(medicineReservation != null){
                medicineReservationService.finishMedicineReservation(medicineReservation);
                emailService.sendNotificationForSuccessTakingMedicineReservation(medicineReservation);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacistInfoDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.converter.PharmacistConverter;
import com.isa.pharmacies_system.converter.UserConverter;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/pharmacist")
public class PharmacistController {

    private IPharmacistService pharmacistService;
    private UserConverter userConverter;
    private PharmacistConverter pharmacistConverter;

    @Autowired
    public PharmacistController(IPharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
        this.userConverter = new UserConverter();
        this.pharmacistConverter = new PharmacistConverter();
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST') or hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<UserPersonalInfoDTO> getPharmacistPersonalInfo(@PathVariable Long id){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(id);
            return new ResponseEntity<>(userConverter.convertPharmacistPersonalInfoToDTO(pharmacist),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Boolean> updatePharmacistPersonalInfo(@RequestBody UserPersonalInfoDTO pharmacistPersonalInfoDTO){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(pharmacistPersonalInfoDTO.getId());
            pharmacistService.savePharmacist(userConverter.convertDTOToPharmacistPersonalInfo(pharmacistPersonalInfoDTO,pharmacist));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changePharmacistPassword(@RequestBody UserPasswordDTO pharmacistPasswordDTO){
        try {
            if (pharmacistService.changePassword(pharmacistPasswordDTO))
                return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    //#1[3.16]Korak2
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/free/{pharmacyId}", consumes = "application/json")
    public ResponseEntity<List<PharmacistInfoDTO>> getAllPharmacistsWithOpenAppointmentsByPharmacyId(@PathVariable Long pharmacyId, @RequestBody PharmacistAppointmentTimeDTO timeDTO) {

        try {
            if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 5){
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            List<Pharmacist> pharmacists = pharmacistService.getAllPharmacistsWithOpenAppointmentsByPharmacyId(pharmacyId, timeDTO);
            List<PharmacistInfoDTO> pharmacistInfoDTOS = pharmacistConverter.convertPharmacistListToPharmacistInfoDTOList(pharmacists);
            return new ResponseEntity<>(pharmacistInfoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/futureVacationRequest/{id}")
    public ResponseEntity<List<PharmacistVacationRequest>> getAllFuturePharmacistVacationRequest (@PathVariable Long
    id){
        try {
            return new ResponseEntity<>(pharmacistService.getAllFuturePharmacistVacationRequest(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/getPharmacyId/{pharmacistId}")
    public ResponseEntity<Long> getPharmacyIdWherePharmacistWork (@PathVariable Long pharmacistId){
        try {
            return new ResponseEntity<>(pharmacistService.getPharmacyIdWherePharmacistWork(pharmacistId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

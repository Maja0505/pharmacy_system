package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PharmacistPasswordDTO;
import com.isa.pharmacies_system.DTO.PharmacistPersonalInfoDTO;
import com.isa.pharmacies_system.converter.PharmacistConverter;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/pharmacist")
public class PharmacistController {

    private IPharmacistService pharmacistService;
    private PharmacistConverter pharmacistConverter;

    @Autowired
    public PharmacistController(IPharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
        this.pharmacistConverter = new PharmacistConverter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacistPersonalInfoDTO> getPharmacistPersonalInfo(@PathVariable Long id){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(id);
            return new ResponseEntity<>(pharmacistConverter.convertPersonalInfoToDTO(pharmacist),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Boolean> updatePharmacistPersonalInfo(@RequestBody PharmacistPersonalInfoDTO pharmacistPersonalInfoDTO){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(pharmacistPersonalInfoDTO.getId());
            pharmacistService.savePharmacist(pharmacistConverter.convertDTOToPersonalInfo(pharmacistPersonalInfoDTO,pharmacist));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changePharmacistPassword(@RequestBody PharmacistPasswordDTO pharmacistPasswordDTO){

        if (pharmacistService.changePassword(pharmacistPasswordDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }


    }
}

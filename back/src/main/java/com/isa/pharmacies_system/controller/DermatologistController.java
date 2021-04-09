package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.DermatologistPasswordDTO;
import com.isa.pharmacies_system.DTO.DermatologistPersonalInfoDTO;
import com.isa.pharmacies_system.converter.DermatologistConverter;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.service.iService.IDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/dermatologist")
public class DermatologistController {

    private IDermatologistService dermatologistService;
    private DermatologistConverter dermatologistConverter;

    @Autowired
    public DermatologistController(IDermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
        this.dermatologistConverter = new DermatologistConverter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DermatologistPersonalInfoDTO> getDermatologistPersonalInfo(@PathVariable Long id){
        try {
            Dermatologist dermatologist = dermatologistService.getDermatologist(id);
            return new ResponseEntity<>(dermatologistConverter.convertPersonalInfoToDTO(dermatologist), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Boolean> updateDermatologistPersonalInfo(@RequestBody DermatologistPersonalInfoDTO dermatologistPersonalInfoDTO){
        try {
            Dermatologist dermatologist = dermatologistService.getDermatologist(dermatologistPersonalInfoDTO.getId());
            dermatologistService.saveDermatologist(dermatologistConverter.convertDTOToPersonalInfo(dermatologistPersonalInfoDTO,dermatologist));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changeDermatologistPassword(@RequestBody DermatologistPasswordDTO dermatologistPasswordDTO){

        if (dermatologistService.changePassword(dermatologistPasswordDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }


    }

}

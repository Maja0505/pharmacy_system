package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.converter.UserConverter;
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
    private UserConverter userConverter;

    @Autowired
    public DermatologistController(IDermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
        this.userConverter = new UserConverter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPersonalInfoDTO> getDermatologistPersonalInfo(@PathVariable Long id){
        try {
            Dermatologist dermatologist = dermatologistService.getDermatologist(id);
            return new ResponseEntity<>(userConverter.convertDermatologistPersonalInfoToDTO(dermatologist), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Boolean> updateDermatologistPersonalInfo(@RequestBody UserPersonalInfoDTO dermatologistPersonalInfoDTO){
        try {
            Dermatologist dermatologist = dermatologistService.getDermatologist(dermatologistPersonalInfoDTO.getId());
            dermatologistService.saveDermatologist(userConverter.convertDTOToDermatologistPersonalInfo(dermatologistPersonalInfoDTO,dermatologist));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changeDermatologistPassword(@RequestBody UserPasswordDTO dermatologistPasswordDTO){
        try {
            if (dermatologistService.changePassword(dermatologistPasswordDTO))
                return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}

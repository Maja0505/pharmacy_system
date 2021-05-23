package com.isa.pharmacies_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.isa.pharmacies_system.DTO.DermatologistNewDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.converter.UserConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.service.iService.IDermatologistService;


@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/dermatologist")
public class DermatologistController {

    private IDermatologistService dermatologistService;
    private UserConverter userConverter;

    @Autowired
    public DermatologistController(IDermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
        this.userConverter = new UserConverter();
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<UserPersonalInfoDTO> getDermatologistPersonalInfo(@PathVariable Long id){
        try {
            return new ResponseEntity<>(userConverter.convertDermatologistPersonalInfoToDTO(dermatologistService.getById(id)), HttpStatus.OK);
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
    
    @GetMapping(value = "/all")
	public ResponseEntity<List<Dermatologist>> getAllPharmacies() {
		return new ResponseEntity<>(dermatologistService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<Dermatologist> createNewPharmacyAdmin(@RequestBody DermatologistNewDTO dermatologistNewDTO) {
		try {
			return new ResponseEntity<>(dermatologistService.create(dermatologistNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

    @GetMapping("/futureVacationRequest/{id}")
    public ResponseEntity<List<DermatologistVacationRequest>> getFutureDermatologistVacationRequest(@PathVariable Long id){
        try {
            return new ResponseEntity<>(dermatologistService.getAllFutureDermatologistVacation(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }







}

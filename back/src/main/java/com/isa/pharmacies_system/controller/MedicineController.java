package com.isa.pharmacies_system.controller;

import java.util.List;

import com.isa.pharmacies_system.DTO.MedicineForAllergiesDTO;
import com.isa.pharmacies_system.converter.MedicineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.DTO.MedicineReviewDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineInfo;
import com.isa.pharmacies_system.service.iService.IMedicineService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(value = "api/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineController {

    private IMedicineService medicineService;
    private MedicineConverter medicineConverter;

    @Autowired
    public MedicineController(IMedicineService medicineService) {
        this.medicineService = medicineService;
        this.medicineConverter = new MedicineConverter();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(medicineService.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
    
    @GetMapping(value = "/name/{name}")
	public ResponseEntity<Medicine> getMedicineByName(@PathVariable String name) {
		try {
			return new ResponseEntity<>(medicineService.getMedicineByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<MedicineNewDTO>> getAllMedicines() {
		return new ResponseEntity<>(medicineConverter.convertMedicinesToMedicineNewDTOS(medicineService.getAll()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<MedicineReviewDTO>> getAllMedicinesWithPharmaciesAndPrices() {
		return new ResponseEntity<>(medicineService.getMedicinesAndPharmacyWithMedicines(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/medicineInfo/{medicineName}")
	public ResponseEntity<MedicineInfo> getMedicineInfoByMedicineName(@PathVariable String medicineName) {
		return new ResponseEntity<>(medicineService.getMedicineInfoByMedicineName(medicineName), HttpStatus.OK);
	}


	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/all/short")
	public ResponseEntity<List<MedicineForAllergiesDTO>> getAllMedicinesShortVersion() {
		return new ResponseEntity<>(medicineConverter.convertMedicineListToMedicineForAllergiesDTOList(medicineService.getAll()), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<Medicine> createNewMedicineInSystem(@RequestBody MedicineNewDTO medicineNewDTO) {
		try {
			return new ResponseEntity<>(medicineService.create(medicineNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

package com.isa.pharmacies_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.isa.pharmacies_system.DTO.PharmacyAdminNewDTO;
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;
import com.isa.pharmacies_system.service.iService.IPharmacyAdminService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(value = "api/pharmacyAdmin")
public class PharmacyAdminController {
	private IPharmacyAdminService iPharmacyAdminService;

	@Autowired
	public PharmacyAdminController(IPharmacyAdminService iPharmacyAdminService) {
		this.iPharmacyAdminService = iPharmacyAdminService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyAdmin> findOneForPharmacyAdmin(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iPharmacyAdminService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<PharmacyAdmin>> getAllPharmacies() {
		return new ResponseEntity<>(iPharmacyAdminService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<PharmacyAdmin> createNewPharmacyAdmin(@RequestBody PharmacyAdminNewDTO pharmacyNewDTO) {
		try {
			return new ResponseEntity<>(iPharmacyAdminService.create(pharmacyNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

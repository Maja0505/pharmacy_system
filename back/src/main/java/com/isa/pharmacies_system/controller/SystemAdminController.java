package com.isa.pharmacies_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.pharmacies_system.DTO.SystemAdminNewDTO;
import com.isa.pharmacies_system.domain.user.SystemAdmin;
import com.isa.pharmacies_system.service.iService.ISystemAdminService;

@Controller
@RequestMapping(value = "api/systemAdmin")
public class SystemAdminController {
	
	private ISystemAdminService iSystemAdminService;
	
	@Autowired
	public SystemAdminController(ISystemAdminService iSystemAdminService) {
		this.iSystemAdminService = iSystemAdminService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SystemAdmin> getSystemAdminById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iSystemAdminService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<SystemAdmin>> getAllSystemAdmins() {
		return new ResponseEntity<>(iSystemAdminService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<SystemAdmin> createNewSystemAdmin(@RequestBody SystemAdminNewDTO systemAdminNewDTO) {
		try {
			return new ResponseEntity<>(iSystemAdminService.create(systemAdminNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

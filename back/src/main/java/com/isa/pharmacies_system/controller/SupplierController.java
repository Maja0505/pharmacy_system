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

import com.isa.pharmacies_system.DTO.SupplierNewDTO;
import com.isa.pharmacies_system.domain.user.Supplier;
import com.isa.pharmacies_system.service.iService.ISupplierService;

@Controller
@RequestMapping(value = "api/supplier")
public class SupplierController {
	private ISupplierService iSupplierService;

	@Autowired
	public SupplierController(ISupplierService iSupplierService) {
		this.iSupplierService = iSupplierService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iSupplierService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Supplier>> getAllSuppliers() {
		return new ResponseEntity<>(iSupplierService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<Supplier> createNewSupplier(@RequestBody SupplierNewDTO supplierNewDTO) {
		try {
			return new ResponseEntity<>(iSupplierService.create(supplierNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

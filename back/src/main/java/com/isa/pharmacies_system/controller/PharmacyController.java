package com.isa.pharmacies_system.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Controller
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	private IPharmacyService iPharmacyService;
	private PharmacyConverter pharmacyConverter;
	private IPriceListService priceListService;

	@Autowired
	public PharmacyController(IPharmacyService iPharmacyService,IPriceListService priceListService)
	{
		this.iPharmacyService = iPharmacyService;
		this.priceListService = priceListService;
		this.pharmacyConverter = new PharmacyConverter();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pharmacy> findOneForPharmacyAdmin(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(iPharmacyService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
		return new ResponseEntity<>(iPharmacyService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = "application/json")
	public ResponseEntity<Pharmacy> createNewPharmacy(@RequestBody PharmacyNewDTO pharmacyNewDTO) {
		try {
			// dodati kreiranje cjenovnika (nekog fiksnog) i storage-a za apoteku
			return new ResponseEntity<>(iPharmacyService.create(pharmacyNewDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	//#1[3.16]-korak1
	@GetMapping(value = "/free", consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getAllPharmacyWithFreePharmacistByDate(@RequestBody PharmacistAppointmentTimeDTO timeDTO){

		try {
			if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 10){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<Pharmacy> pharmacies = iPharmacyService.getAllPharmacyWithFreePharmacistByDate(timeDTO);
			List<PharmacyDTO> pharmacyDTOS = new ArrayList<>();
			for (Pharmacy pharmacy: pharmacies) {
				PharmacyDTO pharmacyDTO = pharmacyConverter.convertPharmacyToPharmacyDTO(pharmacy);
				pharmacyDTOS.add(priceListService.addPriceListToPharmacyDTO(pharmacyDTO));
			}
			return new ResponseEntity<>(pharmacyDTOS,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}

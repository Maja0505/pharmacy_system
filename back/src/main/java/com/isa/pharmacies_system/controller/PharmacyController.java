package com.isa.pharmacies_system.controller;


import java.time.LocalDateTime;
import java.util.List;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	private final IPharmacyService iPharmacyService;
	private final PharmacyConverter pharmacyConverter;

	@Autowired
	public PharmacyController(IPharmacyService iPharmacyService,IPriceListService priceListService)
	{
		this.iPharmacyService = iPharmacyService;
		this.pharmacyConverter = new PharmacyConverter(priceListService);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyDTO> findOneForPharmacyAdmin(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(pharmacyConverter.convertPharmacyToPharmacyDTO(iPharmacyService.getById(id)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/all/{page}")
	public ResponseEntity<List<PharmacyDTO>> getAllPharmaciesWithPages(@PathVariable int page) {
		return new ResponseEntity<>(pharmacyConverter.convertPharmacyListToPharmacyDTOList(iPharmacyService.getAllWithPages(page).toList()), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {
		return new ResponseEntity<>(pharmacyConverter.convertPharmacyListToPharmacyDTOList(iPharmacyService.getAll()), HttpStatus.OK);
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
	@PutMapping(value = "/free", consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getAllPharmacyWithFreePharmacistByDate(@RequestBody PharmacistAppointmentTimeDTO timeDTO){

		try {
			if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 10){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<Pharmacy> pharmacies = iPharmacyService.getAllPharmacyWithFreePharmacistByDate(timeDTO);
			return new ResponseEntity<>(pharmacyConverter.convertPharmacyListToPharmacyDTOList(pharmacies),HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/sortByName/{asc}",consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getSortedPharmacyByName(@RequestBody List<PharmacyDTO> pharmacies, @PathVariable String asc){
		try {
			if(asc.equals("asc")){
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyName(pharmacies,true),HttpStatus.OK);
			}else{
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyName(pharmacies,false),HttpStatus.OK);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/sortByRating/{asc}",consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getSortedPharmacyByRating(@RequestBody List<PharmacyDTO> pharmacies, @PathVariable String asc){
		try {
			if(asc.equals("asc")){
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyRating(pharmacies,true),HttpStatus.OK);
			}else{
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyRating(pharmacies,false),HttpStatus.OK);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}

	}

}

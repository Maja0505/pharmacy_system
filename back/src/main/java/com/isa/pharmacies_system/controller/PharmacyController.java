package com.isa.pharmacies_system.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Controller
@CrossOrigin(origins="*")
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

	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyProfileDTO> findOneForPharmacyAdmin(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(pharmacyConverter.convertPharmacyToPharmacyProfileDTO(iPharmacyService.getById(id)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

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
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PutMapping(value = "/free", consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getAllPharmacyWithFreePharmacistByDate(@RequestBody PharmacistAppointmentTimeDTO timeDTO){

		try {
			if(!timeDTO.getStartTime().isAfter(LocalDateTime.now()) || timeDTO.getDuration() < 5){
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
			List<Pharmacy> pharmacies = iPharmacyService.getAllPharmacyWithFreePharmacistByDate(timeDTO);
			return new ResponseEntity<>(pharmacyConverter.convertPharmacyListToPharmacyDTOList(pharmacies),HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
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

	@PutMapping(value = "/search/{word}", consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> searchPharmacyByNameAndCity(@PathVariable String word, @RequestBody List<PharmacyDTO> pharmacyDTOS){
		try {
			return new ResponseEntity<>(iPharmacyService.searchPharmacyByNameAndCity(word,pharmacyDTOS),HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/filter", consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> filterPharmacy(@RequestBody FilteringPharmacyDTO filteringPharmacyDTO) {
		try {
			return new ResponseEntity<>(iPharmacyService.filterPharmacy(filteringPharmacyDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/sortByCity/{asc}",consumes = "application/json")
	public ResponseEntity<List<PharmacyDTO>> getSortedPharmacyByCity(@RequestBody List<PharmacyDTO> pharmacies, @PathVariable String asc){
		try {
			if(asc.equals("asc")){
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyCity(pharmacies,true),HttpStatus.OK);
			}else{
				return new ResponseEntity<>(iPharmacyService.sortByPharmacyCity(pharmacies,false),HttpStatus.OK);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}

	//Nemanja
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	@GetMapping("/getPharmacies/{dermatologistId}")
	public ResponseEntity<List<PharmacyWhereDermatologistWorkDTO>> getAllPharmaciesWhereDermatologistWork(@PathVariable Long dermatologistId){
		try {
			List<Pharmacy> pharmaciesWhereDermatologistWork = iPharmacyService.getAllPharmacyByDermatologistId(dermatologistId);
			if(pharmaciesWhereDermatologistWork != null){
				List<PharmacyWhereDermatologistWorkDTO> pharmacyWhereDermatologistWorkDTOS = pharmacyConverter.convertPharmacyListToPharmacyWhereDermatologistWorkDTOList(pharmaciesWhereDermatologistWork);
				return new ResponseEntity<>(pharmacyWhereDermatologistWorkDTOS,HttpStatus.OK);
			}
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}catch (Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
}

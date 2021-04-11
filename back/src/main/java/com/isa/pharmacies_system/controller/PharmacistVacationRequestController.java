package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
import com.isa.pharmacies_system.converter.VacationRequestConverter;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.service.iService.IPharmacistVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/pharmacistVacationRequest")
public class PharmacistVacationRequestController {

    private IPharmacistVacationRequestService pharmacistVacationRequestService;
    private VacationRequestConverter vacationRequestConverter;

    @Autowired
    public PharmacistVacationRequestController(IPharmacistVacationRequestService pharmacistVacationRequestService) {
        this.pharmacistVacationRequestService = pharmacistVacationRequestService;
        this.vacationRequestConverter = new VacationRequestConverter();
    }

    @GetMapping("/")
    public ResponseEntity<List<PharmacistVacationRequest>> getAllPharmacistVacationRequest(){
        try {
            return new ResponseEntity<>(pharmacistVacationRequestService.getAllPharmacistVacationRequest(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create",consumes = "application/json")
    public ResponseEntity<Boolean> createPharmacistVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO){
       try {
           PharmacistVacationRequest pharmacistVacationRequest = vacationRequestConverter.convertVacationRequestDTOToPharmacistRequest(vacationRequestDTO);
           if(pharmacistVacationRequestService.createPharmacistVacationRequest(pharmacistVacationRequest,vacationRequestDTO.getStaffId())){
               return new ResponseEntity<>(HttpStatus.CREATED);
           }else{
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
       }

    }

}

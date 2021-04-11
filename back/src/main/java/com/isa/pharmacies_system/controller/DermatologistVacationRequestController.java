package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
import com.isa.pharmacies_system.converter.VacationRequestConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.service.iService.IDermatologistVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/dermatologistVacationRequest")
public class DermatologistVacationRequestController {

    private IDermatologistVacationRequestService dermatologistVacationRequestService;
    private VacationRequestConverter vacationRequestConverter;

    @Autowired
    public DermatologistVacationRequestController(IDermatologistVacationRequestService dermatologistVacationRequestService) {
        this.dermatologistVacationRequestService = dermatologistVacationRequestService;
        this.vacationRequestConverter = new VacationRequestConverter();
    }

    @GetMapping("/")
    public ResponseEntity<List<DermatologistVacationRequest>> getAllDermatologistVacationRequest(){
        try {
            return new ResponseEntity<>(dermatologistVacationRequestService.getAllDermatologistVacationRequest(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create",consumes = "application/json")
    public ResponseEntity<Boolean> createDermatologistVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO){
        try {
            DermatologistVacationRequest dermatologistVacationRequest = vacationRequestConverter.convertVacationRequestDTOToDermatologistRequest(vacationRequestDTO);
            if(dermatologistVacationRequestService.createDermatologistVacationRequest(dermatologistVacationRequest,vacationRequestDTO.getStaffId())){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

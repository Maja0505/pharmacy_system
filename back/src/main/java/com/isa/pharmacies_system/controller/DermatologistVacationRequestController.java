package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
import com.isa.pharmacies_system.converter.VacationRequestConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.service.iService.IDermatologistVacationRequestService;
import com.isa.pharmacies_system.service.iService.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("api/dermatologistVacationRequest")
public class DermatologistVacationRequestController {

    private IDermatologistVacationRequestService dermatologistVacationRequestService;
    private VacationRequestConverter vacationRequestConverter;
    private IVacationRequestService vacationRequestService;

    @Autowired
    public DermatologistVacationRequestController(IDermatologistVacationRequestService dermatologistVacationRequestService, IVacationRequestService vacationRequestService) {
        this.dermatologistVacationRequestService = dermatologistVacationRequestService;
        this.vacationRequestService = vacationRequestService;
        this.vacationRequestConverter = new VacationRequestConverter();
    }

    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @GetMapping("/")
    public ResponseEntity<List<DermatologistVacationRequest>> getAllDermatologistVacationRequest(){
        try {
            return new ResponseEntity<>(dermatologistVacationRequestService.getAllDermatologistVacationRequest(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    @PostMapping(value = "/create",consumes = "application/json")
    public ResponseEntity<Boolean> createDermatologistVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO){
        try {
            DermatologistVacationRequest dermatologistVacationRequest = vacationRequestConverter.convertVacationRequestDTOToDermatologistRequest(vacationRequestDTO);
            List<VacationRequest> list = dermatologistVacationRequestService.makeVacationRequestFromDermatologistRequest(vacationRequestDTO.getStaffId());
            if(checkIsDermatologistVacationRequestCreated(dermatologistVacationRequest,vacationRequestDTO.getStaffId(),list)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private Boolean checkIsDermatologistVacationRequestCreated(DermatologistVacationRequest dermatologistVacationRequest,Long dermatologistId,List<VacationRequest> vacationRequestList) {
        if (vacationRequestService.checkVacationRequest(dermatologistVacationRequest, vacationRequestList)) {
            return dermatologistVacationRequestService.createDermatologistVacationRequest(dermatologistVacationRequest, dermatologistId);
        }
        return false;
    }
}

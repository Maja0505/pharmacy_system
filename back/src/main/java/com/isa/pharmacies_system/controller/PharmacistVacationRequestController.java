package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
import com.isa.pharmacies_system.converter.VacationRequestConverter;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.service.iService.IPharmacistVacationRequestService;
import com.isa.pharmacies_system.service.iService.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/pharmacistVacationRequest")
public class PharmacistVacationRequestController {

    private IPharmacistVacationRequestService pharmacistVacationRequestService;
    private VacationRequestConverter vacationRequestConverter;
    private IVacationRequestService vacationRequestService;

    @Autowired
    public PharmacistVacationRequestController(IPharmacistVacationRequestService pharmacistVacationRequestService, IVacationRequestService vacationRequestService) {
        this.pharmacistVacationRequestService = pharmacistVacationRequestService;
        this.vacationRequestService = vacationRequestService;
        this.vacationRequestConverter = new VacationRequestConverter();
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/")
    public ResponseEntity<List<PharmacistVacationRequest>> getAllPharmacistVacationRequest(){
        try {
            return new ResponseEntity<>(pharmacistVacationRequestService.getAllPharmacistVacationRequest(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PostMapping(value = "/create",consumes = "application/json")
    public ResponseEntity<Boolean> createPharmacistVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO){
       try {
           PharmacistVacationRequest pharmacistVacationRequest = vacationRequestConverter.convertVacationRequestDTOToPharmacistRequest(vacationRequestDTO);
           List<VacationRequest> list = pharmacistVacationRequestService.makeVacationRequestListFromPharmacistRequestList(vacationRequestDTO.getStaffId());
           if(checkIsPharmacistVacationRequestCreated(pharmacistVacationRequest,vacationRequestDTO.getStaffId(),list)){
               return new ResponseEntity<>(HttpStatus.CREATED);
           }else{
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
       }

    }

    private Boolean checkIsPharmacistVacationRequestCreated(PharmacistVacationRequest pharmacistVacationRequest, Long pharmacistId, List<VacationRequest> vacationRequestList){
        if(vacationRequestService.checkVacationRequest(pharmacistVacationRequest,vacationRequestList)){
            return pharmacistVacationRequestService.createPharmacistVacationRequest(pharmacistVacationRequest,pharmacistId);
        }
        return false;
    }

}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.StaffPasswordDTO;
import com.isa.pharmacies_system.DTO.StaffPersonalInfoDTO;
import com.isa.pharmacies_system.converter.StaffConverter;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/pharmacist")
public class PharmacistController {

    private IPharmacistService pharmacistService;
    private StaffConverter staffConverter;

    @Autowired
    public PharmacistController(IPharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
        this.staffConverter = new StaffConverter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffPersonalInfoDTO> getPharmacistPersonalInfo(@PathVariable Long id){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(id);
            return new ResponseEntity<>(staffConverter.convertPharmacistPersonalInfoToDTO(pharmacist),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Boolean> updatePharmacistPersonalInfo(@RequestBody StaffPersonalInfoDTO pharmacistPersonalInfoDTO){
        try {
            Pharmacist pharmacist = pharmacistService.getPharmacist(pharmacistPersonalInfoDTO.getId());
            pharmacistService.savePharmacist(staffConverter.convertDTOToPharmacistPersonalInfo(pharmacistPersonalInfoDTO,pharmacist));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<Boolean> changePharmacistPassword(@RequestBody StaffPasswordDTO pharmacistPasswordDTO){
        try {
            if (pharmacistService.changePassword(pharmacistPasswordDTO))
                return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }
}

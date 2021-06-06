package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.EPrescriptionDTO;
import com.isa.pharmacies_system.DTO.EPrescriptionItemDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.converter.EPrescriptionItemConverter;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.service.iService.IEPrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/ePrescription")
public class EPrescriptionController {

    private IEPrescriptionService ePrescriptionService;
    private EPrescriptionItemConverter ePrescriptionItemConverter;

    public EPrescriptionController(IEPrescriptionService ePrescriptionService) {
        this.ePrescriptionService = ePrescriptionService;
        this.ePrescriptionItemConverter = new EPrescriptionItemConverter();
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping(value = "/{id}/ePrescriptionItems")
    public ResponseEntity<List<EPrescriptionItemDTO>> getAllEPrescriptionItemForEPrescription(@PathVariable Long id) {

        try {
            List<EPrescriptionItemDTO> ePrescriptionItemDTOS = ePrescriptionItemConverter.convertEPrescriptionItemsToEPrescriptionItemDTOS(ePrescriptionService.getAllEPrescriptionItemForEPrescription(id));
            return new ResponseEntity<>(ePrescriptionItemDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/sortByDate/{asc}",consumes = "application/json")
    public ResponseEntity<List<EPrescriptionDTO>> getSortedEPrescritpionByDate(@RequestBody List<EPrescriptionDTO> ePrescriptions, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(ePrescriptionService.sortByEPrescriptionByDate(ePrescriptions,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(ePrescriptionService.sortByEPrescriptionByDate(ePrescriptions,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/sortByStatus/{asc}",consumes = "application/json")
    public ResponseEntity<List<EPrescriptionDTO>> getSortedEPrescritpionByStatus(@RequestBody List<EPrescriptionDTO> ePrescriptions, @PathVariable String asc){

        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(ePrescriptionService.sortByEPrescriptionByStatus(ePrescriptions,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(ePrescriptionService.sortByEPrescriptionByStatus(ePrescriptions,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
}

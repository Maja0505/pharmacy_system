package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.EPrescriptionItemDTO;
import com.isa.pharmacies_system.converter.EPrescriptionItemConverter;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.service.iService.IEPrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
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
}

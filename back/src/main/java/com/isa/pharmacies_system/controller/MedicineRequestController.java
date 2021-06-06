package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.service.iService.IMedicineRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/medicineRequest")
public class MedicineRequestController {

    private IMedicineRequestService medicineRequestService;

    @Autowired
    public MedicineRequestController(IMedicineRequestService medicineRequestService) {
        this.medicineRequestService = medicineRequestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicineRequest>> getAllMedicineRequest(){
        try {
            return new ResponseEntity<>(medicineRequestService.getAllMedicineRequest(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

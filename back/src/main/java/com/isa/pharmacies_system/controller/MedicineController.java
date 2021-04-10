package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.service.iService.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineController {

    private IMedicineService medicineService;

    @Autowired
    public MedicineController(IMedicineService medicineService) {
        this.medicineService = medicineService;
    }

}

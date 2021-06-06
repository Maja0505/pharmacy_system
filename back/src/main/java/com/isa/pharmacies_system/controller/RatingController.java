package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.RatingMedicineDTO;
import com.isa.pharmacies_system.DTO.RatingPharmacyDTO;
import com.isa.pharmacies_system.DTO.RatingStaffDTO;
import com.isa.pharmacies_system.service.iService.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(value = "api/rating" , produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {

    private IRatingService ratingService;

    @Autowired
    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PostMapping(value = "/staff", consumes = {"application/json"})
    public ResponseEntity<Boolean> setStaffRating(@RequestBody RatingStaffDTO ratingStaffDTO) {

        try {

            if (ratingService.setStaffRating(ratingStaffDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PostMapping(value = "/medicine", consumes = {"application/json"})
    public ResponseEntity<Boolean> setMedicineRating(@RequestBody RatingMedicineDTO medicineDTO) {

        try {

            if (ratingService.setMedicineRating(medicineDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PostMapping(value = "/pharmacy")
    public ResponseEntity<?> setPharmacyRating(@RequestBody RatingPharmacyDTO pharmacyDTO) {

        try {

            if (ratingService.setPharmacyRating(pharmacyDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

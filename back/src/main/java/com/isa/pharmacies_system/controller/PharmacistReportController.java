package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.DTO.ReportForPatientDTO;
import com.isa.pharmacies_system.converter.MedicineRequestConverter;
import com.isa.pharmacies_system.converter.PharmacistReportConverter;
import com.isa.pharmacies_system.converter.ReportConverter;
import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.service.iService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/pharmacistReport")
public class PharmacistReportController {

    private IPharmacistReportService pharmacistReportService;
    private PharmacistReportConverter pharmacistReportConverter;
    private IPharmacyStorageItemService pharmacyStorageItemService;
    private ReportConverter reportConverter;
    private IMedicineService medicineService;
    private IMedicineRequestService medicineRequestService;
    private MedicineRequestConverter medicineRequestConverter;

    @Autowired
    public PharmacistReportController(IDermatologistReportService dermatologistReportService, IPharmacistReportService pharmacistReportService, IPharmacyStorageItemService pharmacyStorageItemService, IMedicineService medicineService, IMedicineRequestService medicineRequestService) {
        this.pharmacistReportService = pharmacistReportService;
        this.pharmacyStorageItemService = pharmacyStorageItemService;
        this.medicineService = medicineService;
        this.medicineRequestService = medicineRequestService;
        this.pharmacistReportConverter = new PharmacistReportConverter();
        this.reportConverter = new ReportConverter();
        this.medicineRequestConverter = new MedicineRequestConverter();

    }

    //#1]
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/all/patient/{idPatient}")
    public ResponseEntity<List<PharmacistReportDTO>> findAllForPatient(@PathVariable Long idPatient){
        try{
            List<PharmacistReportDTO> pharmacistReportDTOS = pharmacistReportConverter.convertPharmacistReportToPharmacistReportDTOS(pharmacistReportService.findAllPharmacistReportForPatient(idPatient));
            return new ResponseEntity<>(pharmacistReportDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PostMapping(value = "/create",consumes="application/json")
    public ResponseEntity<Boolean> createPharmacistReport(@RequestBody ReportForPatientDTO reportForPatientDTO){
        try {
            RecipeItemDTO recipeItemWithInsufficientQuantity = pharmacyStorageItemService.checkHaveEnoughMedicineAmountForEveryRecipeItem(reportForPatientDTO.getRecipeItemsDTO());
            if(recipeItemWithInsufficientQuantity == null){
                PharmacistReport pharmacistReport = reportConverter.convertReportDTOTOPharmacistReport(reportForPatientDTO,medicineService);
                if(pharmacistReportService.createPharmacistReport(pharmacistReport,reportForPatientDTO.getAppointmentId())){
                    pharmacyStorageItemService.subtractPharmacyStorageItemMedicineAmountForEveryRecipeItem(reportForPatientDTO.getRecipeItemsDTO());
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }
            }
            assert recipeItemWithInsufficientQuantity != null;
            PharmacyStorageItem pharmacyStorageItemWithNotEnoughAmount = pharmacyStorageItemService.findOne(recipeItemWithInsufficientQuantity.getPharmacyItemId());
            MedicineRequest medicineRequest = medicineRequestConverter.convertPharmacyStorageItemToMedicineRequest(pharmacyStorageItemWithNotEnoughAmount);
            medicineRequestService.createMedicineRequest(medicineRequest);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/sortByDate/{asc}",consumes = "application/json")
    public ResponseEntity<List<PharmacistReportDTO>> getSortedPharmacyByDate(@RequestBody List<PharmacistReportDTO> pharmacistReports, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyDate(pharmacistReports,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyDate(pharmacistReports,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/sortByDuration/{asc}",consumes = "application/json")
    public ResponseEntity<List<PharmacistReportDTO>> getSortedPharmacyByDuration(@RequestBody List<PharmacistReportDTO> pharmacistReports, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyDuration(pharmacistReports,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyDuration(pharmacistReports,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @PutMapping(value = "/sortByPrice/{asc}",consumes = "application/json")
    public ResponseEntity<List<PharmacistReportDTO>> getSortedPharmacyByPrice(@RequestBody List<PharmacistReportDTO> pharmacistReports, @PathVariable String asc){
        try {
            if(asc.equals("asc")){
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyPrice(pharmacistReports,true),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(pharmacistReportService.sortByPharmacyPrice(pharmacistReports,false),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }


}

package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.DTO.ReportForPatientDTO;
import com.isa.pharmacies_system.converter.MedicineRequestConverter;
import com.isa.pharmacies_system.converter.ReportConverter;
import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.service.MedicineRequestService;
import com.isa.pharmacies_system.service.iService.IDermatologistReportService;
import com.isa.pharmacies_system.service.iService.IMedicineRequestService;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageItemService;
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
@RequestMapping("api/dermatologistReport")
public class DermatologistReportController {

    private IDermatologistReportService dermatologistReportService;
    private IMedicineService medicineService;
    private ReportConverter reportConverter;
    private IPharmacyStorageItemService pharmacyStorageItemService;
    private IMedicineRequestService medicineRequestService;
    private MedicineRequestConverter medicineRequestConverter;

    @Autowired
    public DermatologistReportController(IDermatologistReportService dermatologistReportService, IMedicineService medicineService, IPharmacyStorageItemService pharmacyStorageItemService, IMedicineRequestService medicineRequestService) {
        this.dermatologistReportService = dermatologistReportService;
        this.medicineService = medicineService;
        this.pharmacyStorageItemService = pharmacyStorageItemService;
        this.medicineRequestService = medicineRequestService;
        this.reportConverter = new ReportConverter();
        this.medicineRequestConverter = new MedicineRequestConverter();
    }

    @GetMapping("/all")
    public ResponseEntity<List<DermatologistReport>> findAll(){
        return new ResponseEntity<>(dermatologistReportService.findAll(), HttpStatus.OK);
    }

    //Nemanja
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Boolean> createDermatologistReport(@RequestBody ReportForPatientDTO reportForPatientDTO){
        try {
            RecipeItemDTO recipeItemWithNotEnoughAmount = pharmacyStorageItemService.checkHaveEnoughMedicineAmountForEveryRecipeItem(reportForPatientDTO.getRecipeItemsDTO());
             if(recipeItemWithNotEnoughAmount != null){
                 PharmacyStorageItem pharmacyStorageItemWithNotEnoughAmount = pharmacyStorageItemService.findOne(recipeItemWithNotEnoughAmount.getPharmacyItemId());
                 MedicineRequest medicineRequest = medicineRequestConverter.convertPharmacyStorageItemToMedicineRequest(pharmacyStorageItemWithNotEnoughAmount);
                medicineRequestService.createMedicineRequest(medicineRequest);
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            DermatologistReport dermatologistReport = reportConverter.convertReportDTOToDermatologistReport(reportForPatientDTO,medicineService);
            if(dermatologistReportService.createDermatologistReport(dermatologistReport,reportForPatientDTO.getAppointmentId())){
                pharmacyStorageItemService.subtractPharmacyStorageItemMedicineAmountForEveryRecipeItem(reportForPatientDTO.getRecipeItemsDTO());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
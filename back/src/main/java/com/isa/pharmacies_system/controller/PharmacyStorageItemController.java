package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.MedicineForRecipeDTO;
import com.isa.pharmacies_system.DTO.PharmacyWithMedicinePriceDTO;
import com.isa.pharmacies_system.converter.MedicineConverter;
import com.isa.pharmacies_system.converter.MedicineRequestConverter;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IMedicinePriceRepository;
import com.isa.pharmacies_system.service.iService.IMedicineRequestService;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageItemService;
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
@RequestMapping(value = "api/pharmacyStorageItem")
public class PharmacyStorageItemController {

    private IPharmacyStorageItemService pharmacyStorageItemService;
    private PharmacyConverter pharmacyConverter;
    private IMedicinePriceRepository medicinePriceRepository;
    private MedicineConverter medicineConverter;
    private IMedicineRequestService medicineRequestService;
    private MedicineRequestConverter medicineRequestConverter;

    public PharmacyStorageItemController(IPharmacyStorageItemService pharmacyStorageItemService, IMedicinePriceRepository medicinePriceRepository, IMedicineRequestService medicineRequestService) {
        this.pharmacyStorageItemService = pharmacyStorageItemService;
        this.medicinePriceRepository = medicinePriceRepository;
        this.medicineRequestService = medicineRequestService;
        this.pharmacyConverter = new PharmacyConverter();
        this.medicineConverter = new MedicineConverter();
        this.medicineRequestConverter = new MedicineRequestConverter();
    }

    //#1[3.19]
    @GetMapping("/{medicineId}/{patientId}")
    public ResponseEntity<List<PharmacyWithMedicinePriceDTO>> getAllPharmacyStorageItemsWithSelectedMedicine(@PathVariable Long medicineId,@PathVariable Long patientId){
        try {
            List<PharmacyStorageItem> pharmacyStorageItems =  pharmacyStorageItemService.getAllPharmacyStorageItemsWithSelectedMedicine(medicineId,patientId);
            if(pharmacyStorageItems != null) {
                List<PharmacyWithMedicinePriceDTO> pharmacyWithMedicinePriceDTOList = pharmacyConverter.convertListPharmacyStorageItemsToPharmacyWithMedicinePriceDTOS(pharmacyStorageItems);
                for (PharmacyWithMedicinePriceDTO pharmacy : pharmacyWithMedicinePriceDTOList) {
                    pharmacy.setMedicinePrice(medicinePriceRepository.getPriceForMedicineByPharmacyId(pharmacy.getId(), medicineId));
                }
                return new ResponseEntity<>(pharmacyWithMedicinePriceDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST') or hasRole('ROLE_PHARMACIST')")
    @GetMapping("/all/{pharmacyId}")
    public ResponseEntity<List<MedicineForRecipeDTO>> getAllPharmacyStorageItemsInPharmacy(@PathVariable Long pharmacyId){
        try {
            List<PharmacyStorageItem> list = pharmacyStorageItemService.getAllPharmacyStorageItemsInPharmacy(pharmacyId);
            return new ResponseEntity<>(medicineConverter.convertPharmacyStorageItemsToMedicineForRecipeDTO(list),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //Nemanja
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST') or hasRole('ROLE_PHARMACIST')")
    @GetMapping("/check/{itemId}/{medicineAmount}/{patientId}")
    public ResponseEntity<List<MedicineForRecipeDTO>> haveEnoughMedicineAmountForPharmacyStorageItem(@PathVariable Long itemId,@PathVariable Long medicineAmount,@PathVariable Long patientId){
        try {
            Boolean haveEnoughMedicine = pharmacyStorageItemService.haveEnoughMedicineAmountForPharmacyStorageItem(itemId,medicineAmount);
            if(haveEnoughMedicine && !pharmacyStorageItemService.checkHavePatientAllergiesOnMedicine(itemId,patientId)){
                return new ResponseEntity<>(null,HttpStatus.OK);
            }else{
                if(!haveEnoughMedicine){
                    PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemService.findOne(itemId);
                    if(pharmacyStorageItem != null){
                        MedicineRequest medicineRequest = medicineRequestConverter.convertPharmacyStorageItemToMedicineRequest(pharmacyStorageItem);
                        medicineRequestService.createMedicineRequest(medicineRequest);
                    }
                }
                List<PharmacyStorageItem> alternativePharmacyStorageItems = pharmacyStorageItemService.getAllAlternativePharmacyStorageItemsInPharmacyWithEnoughAmount(itemId,medicineAmount);
                List<MedicineForRecipeDTO> alternativeMedicine = medicineConverter.convertPharmacyStorageItemsToMedicineForRecipeDTO(alternativePharmacyStorageItems);
                return new ResponseEntity<>(alternativeMedicine,HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}

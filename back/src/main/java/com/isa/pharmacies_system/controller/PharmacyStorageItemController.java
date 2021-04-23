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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
    @GetMapping("/check/{itemId}/{medicineAmount}")
    public ResponseEntity<Boolean> haveEnoughMedicineAmountForPharmacyStorageItem(@PathVariable Long itemId,@PathVariable Long medicineAmount){
        try {
            if(pharmacyStorageItemService.haveEnoughMedicineAmountForPharmacyStorageItem(itemId,medicineAmount)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemService.findOne(itemId);
                if(pharmacyStorageItem != null){
                    MedicineRequest medicineRequest = medicineRequestConverter.convertPharmacyStorageItemToMedicineRequest(pharmacyStorageItem);
                    medicineRequestService.createMedicineRequest(medicineRequest);
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

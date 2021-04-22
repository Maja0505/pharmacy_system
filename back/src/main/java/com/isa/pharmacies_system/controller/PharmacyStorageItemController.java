package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.PharmacyWithMedicinePriceDTO;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IMedicinePriceRepository;
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

    public PharmacyStorageItemController(IPharmacyStorageItemService pharmacyStorageItemService, IMedicinePriceRepository medicinePriceRepository) {
        this.pharmacyStorageItemService = pharmacyStorageItemService;
        this.medicinePriceRepository = medicinePriceRepository;
        this.pharmacyConverter = new PharmacyConverter();
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
}

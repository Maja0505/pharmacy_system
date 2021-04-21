package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyStorageItemService implements IPharmacyStorageItemService {

    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;
    private IMedicineRepository medicineRepository;
    private IPatientRepository patientRepository;

    public PharmacyStorageItemService(IPharmacyStorageItemRepository pharmacyStorageItemRepository, IMedicineRepository medicineRepository, IPatientRepository patientRepository) {
        this.pharmacyStorageItemRepository = pharmacyStorageItemRepository;
        this.medicineRepository = medicineRepository;
        this.patientRepository = patientRepository;
    }

    //#1[3.19]
    @Override
    public List<PharmacyStorageItem> getAllPharmacyStorageItemsWithSelectedMedicine(Long medicineId, Long patientId){

        if(doesPatientHaveAllergiesOnMedicine(medicineId,patientId)){
            return pharmacyStorageItemRepository.getAllPharmacyStorageItemsWithSelectedMedicine(medicineId);
        }else{
            return null;
        }
    }

    private Boolean doesPatientHaveAllergiesOnMedicine(Long medicineId, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        return patient.getMedicineAllergies().stream().filter(medicine -> medicine.getId() == medicineId).count() == 0;
    }
}

package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.domain.medicine.*;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IMedicineRequestRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PharmacyStorageItemService implements IPharmacyStorageItemService {

    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;
    private IMedicineRepository medicineRepository;
    private IPatientRepository patientRepository;
    private IMedicineRequestRepository medicineRequestRepository;

    public PharmacyStorageItemService(IPharmacyStorageItemRepository pharmacyStorageItemRepository, IMedicineRepository medicineRepository, IPatientRepository patientRepository, IMedicineRequestRepository medicineRequestRepository) {
        this.pharmacyStorageItemRepository = pharmacyStorageItemRepository;
        this.medicineRepository = medicineRepository;
        this.patientRepository = patientRepository;
        this.medicineRequestRepository = medicineRequestRepository;
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
        return patient != null && patient.getMedicineAllergies().stream().filter(medicine -> medicine.getId() == medicineId).count() == 0;
    }

    //Nemanja
    @Override
    public List<PharmacyStorageItem> getAllPharmacyStorageItemsInPharmacy(Long pharmacyId) {
        return pharmacyStorageItemRepository.findAllPharmacyStorageItemsByPharmacyId(pharmacyId);
    }

    //Nemanja
    @Override
    public Boolean haveEnoughMedicineAmountForPharmacyStorageItem(Long id, Long medicineAmount) {
        PharmacyStorageItem item = pharmacyStorageItemRepository.findById(id).orElse(null);
        if(item != null){
            return item.getMedicineAmount() >= medicineAmount;
        }
        return false;
    }

    //Nemanja
    @Override
    public RecipeItemDTO checkHaveEnoughMedicineAmountForEveryRecipeItem(Set<RecipeItemDTO> recipeItemsDTO) {
        for (RecipeItemDTO item:
             recipeItemsDTO) {
            if(!haveEnoughMedicineAmountForPharmacyStorageItem(item.getPharmacyItemId(),item.getMedicineAmount())){
                return item;
            }
        }
        return null;
    }

    //Nemanja
    @Override
    public void updateMedicineAmountForPharmacyStorageItem(Long id, Long medicineAmount) {
        PharmacyStorageItem item = pharmacyStorageItemRepository.findById(id).orElse(null);
        if(item != null){
            item.setMedicineAmount(item.getMedicineAmount() + medicineAmount);
            pharmacyStorageItemRepository.save(item);
        }
    }

    //Nemanja
    @Override
    public void subtractPharmacyStorageItemMedicineAmountForEveryRecipeItem(Set<RecipeItemDTO> recipeItemsDTO) {
        for (RecipeItemDTO item:
             recipeItemsDTO) {
            updateMedicineAmountForPharmacyStorageItem(item.getPharmacyItemId(),-(item.getMedicineAmount()));
        }
    }

    //Nemanja
    @Override
    public PharmacyStorageItem findOne(Long id) {
        return pharmacyStorageItemRepository.findById(id).orElse(null);
    }

    //Nemanja
    @Override
    public Boolean checkHavePatientAllergiesOnMedicine(Long itemId,Long patientId) {
        PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.findById(itemId).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if(patient != null && pharmacyStorageItem != null){
            return patient.getMedicineAllergies().stream()
                    .filter(medicine -> medicine.getId() == pharmacyStorageItem.getMedicineItem().getId()).count() > 0;
        }
        return false;

    }

    //Nemanja
    @Override
    public List<PharmacyStorageItem> getAllAlternativePharmacyStorageItemsInPharmacyWithEnoughAmount(Long itemId,Long medicineAmount) {
        PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.findById(itemId).orElse(null);
        if(pharmacyStorageItem != null){
            Set<MedicineInfo> pharmacyStorageItemMedicineAlternativeItems = pharmacyStorageItem.getMedicineItem().getAlternativeMedicines();
            List<PharmacyStorageItem> allItemsInPharmacy = getAllPharmacyStorageItemsInPharmacy(pharmacyStorageItem.getPharmacyStorageWithItem().getPharmacy().getId());
            return getAlternativePharmacyItems(pharmacyStorageItemMedicineAlternativeItems,allItemsInPharmacy,medicineAmount);
        }
        return null;
    }

    //Nemanja
    private List<PharmacyStorageItem> getAlternativePharmacyItems(Set<MedicineInfo> pharmacyStorageItemMedicineAlternativeItems, List<PharmacyStorageItem> allItemsInPharmacy, Long medicineAmount) {
        List<PharmacyStorageItem> alternativePharmacyItems = new ArrayList<>();
        for (MedicineInfo medicine:
                pharmacyStorageItemMedicineAlternativeItems) {
            for (PharmacyStorageItem item:
                    allItemsInPharmacy) {
                if(medicine.getId() == item.getMedicineItem().getId() && item.getMedicineAmount() >= medicineAmount){
                    alternativePharmacyItems.add(item);
                    break;
                }
            }
        }
        return alternativePharmacyItems;
    }


}

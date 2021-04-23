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
    private void createMedicineRequestForMissingItem(RecipeItemDTO recipeItem) {
        MedicineRequest medicineRequest = new MedicineRequest();
        MedicineRequestItem medicineRequestItem = new MedicineRequestItem();
        PharmacyStorageItem item = pharmacyStorageItemRepository.findById(recipeItem.getPharmacyItemId()).orElse(null);
        if(item != null){
            medicineRequestItem.setMedicineItem(item.getMedicineItem());
            medicineRequestItem.setTypeOfItem(TypeOfItem.Medicine_request_item);
            medicineRequestItem.setMedicineAmount(100);
            medicineRequest.setPharmacy(item.getPharmacyStorageWithItem().getPharmacy());
            medicineRequest.setStateOfMedicineRequest(StateOfMedicineRequest.Unavailable_Medicine);
            medicineRequest.setMedicineRequestItem(medicineRequestItem);
            medicineRequestRepository.save(medicineRequest);
        }
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

}

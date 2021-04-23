package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;

import java.util.List;
import java.util.Set;

public interface IPharmacyStorageItemService {

    List<PharmacyStorageItem> getAllPharmacyStorageItemsWithSelectedMedicine(Long medicineId, Long patientId);
    List<PharmacyStorageItem> getAllPharmacyStorageItemsInPharmacy(Long pharmacyId);
    Boolean haveEnoughMedicineAmountForPharmacyStorageItem(Long id,Long medicineAmount);
    RecipeItemDTO checkHaveEnoughMedicineAmountForEveryRecipeItem(Set<RecipeItemDTO> recipeItemsDTO);
    void updateMedicineAmountForPharmacyStorageItem(Long id,Long medicineAmount);
    void subtractPharmacyStorageItemMedicineAmountForEveryRecipeItem(Set<RecipeItemDTO> recipeItemsDTO);
    PharmacyStorageItem findOne(Long id);
}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;

import java.util.List;

public interface IPharmacyStorageItemService {

    List<PharmacyStorageItem> getAllPharmacyStorageItemsWithSelectedMedicine(Long medicineId, Long patientId);
}

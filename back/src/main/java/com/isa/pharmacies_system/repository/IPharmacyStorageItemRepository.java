package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPharmacyStorageItemRepository extends JpaRepository<PharmacyStorageItem, Long> {

    @Query("select p from PharmacyStorageItem p where p.medicineItem.id = ?1 and p.medicineAmount > 0")
    List<PharmacyStorageItem> getAllPharmacyStorageItemsWithSelectedMedicine(Long medicineId);

    @Query("select p from PharmacyStorageItem p where p.medicineItem.id = ?1 and p.pharmacyStorageWithItem.pharmacy.id = ?2")
    PharmacyStorageItem getSelectedMedicineFromPharmacyStorage(Long medicineId, Long pharmacyId);

    //Nemanja
    @Query("select p from PharmacyStorageItem p where p.pharmacyStorageWithItem.pharmacy.id=?1 and p.medicineAmount > 0")
    List<PharmacyStorageItem> findAllPharmacyStorageItemsByPharmacyId(Long pharmacyId);
}

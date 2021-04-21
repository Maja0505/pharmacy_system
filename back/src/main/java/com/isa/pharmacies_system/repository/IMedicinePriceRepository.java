package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicinePriceRepository extends JpaRepository<MedicinePrice, Long> {

    @Query("select m.medicinePrice from MedicinePrice m where m.medicineWithPrices.id = ?2 and m.priceListForMedicine.pharmacy.id = ?1")
    Double getPriceForMedicineByPharmacyId(Long pharmacyId, Long medicineId);
}

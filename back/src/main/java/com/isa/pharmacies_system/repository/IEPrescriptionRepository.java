package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEPrescriptionRepository extends JpaRepository<EPrescription,Long> {

    @Query("select  e.ePrescriptionItems from EPrescription e where e.id=?1")
    List<EPrescriptionItem> getAllEPrescriptionItemForEPrescription(Long id);
}

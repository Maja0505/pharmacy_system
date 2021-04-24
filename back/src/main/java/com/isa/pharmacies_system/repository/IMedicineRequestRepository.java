package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicineRequestRepository extends JpaRepository<MedicineRequest,Long> {
}

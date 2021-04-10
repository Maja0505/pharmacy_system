package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicineRepository extends JpaRepository<Medicine,Long> {
}

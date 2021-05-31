package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.medicine.MedicineInfo;

public interface IMedicineInfoRepository extends JpaRepository<MedicineInfo, Long> {

}

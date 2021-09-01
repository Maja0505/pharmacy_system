package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;

public interface IEPrescriptionItemRepository extends JpaRepository<EPrescriptionItem, Long> {

}

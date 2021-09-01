package com.isa.pharmacies_system.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.storage.SupplierStorage;

public interface ISupplierStorageRepository extends JpaRepository<SupplierStorage, Long> {

}

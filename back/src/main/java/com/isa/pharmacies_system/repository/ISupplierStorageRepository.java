package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.storage.SupplierStorage;

public interface ISupplierStorageRepository extends JpaRepository<SupplierStorage, Long> {

}

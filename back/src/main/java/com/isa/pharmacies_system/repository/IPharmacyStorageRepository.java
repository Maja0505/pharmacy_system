package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.storage.PharmacyStorage;

public interface IPharmacyStorageRepository extends JpaRepository<PharmacyStorage, Long>{
	
}

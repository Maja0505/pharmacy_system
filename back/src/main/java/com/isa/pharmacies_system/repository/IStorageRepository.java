package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.storage.Storage;

public interface IStorageRepository extends JpaRepository<Storage, Long> {

}

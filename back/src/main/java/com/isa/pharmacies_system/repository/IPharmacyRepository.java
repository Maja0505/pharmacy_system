package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.user.Pharmacist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query("select p.pharmacistsInPharmacy as pharmacists from Pharmacy p where p.id = ?1")
    List<Pharmacist> getAllPharmacistsForPharmacy(Long pharmacyId);

    Page<Pharmacy> findAll(Pageable pageable);
}

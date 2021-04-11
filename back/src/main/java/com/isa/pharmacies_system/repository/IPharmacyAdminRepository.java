package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.user.PharmacyAdmin;

public interface IPharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Long> {

}

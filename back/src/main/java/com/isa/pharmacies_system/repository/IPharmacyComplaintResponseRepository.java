package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.complaint.PharmacyComplaintResponse;

public interface IPharmacyComplaintResponseRepository extends JpaRepository<PharmacyComplaintResponse, Long> {

}

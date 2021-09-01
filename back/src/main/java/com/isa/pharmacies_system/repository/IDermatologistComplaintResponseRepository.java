package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.complaint.DermatologistComplaintResponse;

public interface IDermatologistComplaintResponseRepository extends JpaRepository<DermatologistComplaintResponse, Long> {

}

package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;

public interface IPharmacyComplaintRepository extends JpaRepository<PharmacyComplaint, Long> {

}

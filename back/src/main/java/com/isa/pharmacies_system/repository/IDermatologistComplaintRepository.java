package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.complaint.DermatologistComplaint;

public interface IDermatologistComplaintRepository extends JpaRepository<DermatologistComplaint, Long>{

}

package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}

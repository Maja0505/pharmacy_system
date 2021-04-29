package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {
}

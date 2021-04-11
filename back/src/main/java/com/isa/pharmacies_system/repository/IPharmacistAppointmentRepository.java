package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IPharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment,Long> {

    @Query("SELECT a FROM PharmacistAppointment a WHERE a.pharmacistForAppointment.id = ?1 AND a.pharmacistAppointmentStartTime < CURRENT_TIMESTAMP ")
    Page<PharmacistAppointment> findAllPastPharmacistAppointment(Long id, Pageable pageable);

}

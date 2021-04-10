package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment,Long> {

    @Query("SELECT a FROM PharmacistAppointment a WHERE a.pharmacistForAppointment.id = ?1 AND a.pharmacistAppointmentStartTime < CURRENT_TIMESTAMP ")
    List<PharmacistAppointment> findAllPastPharmacistAppointment(Long id);

}
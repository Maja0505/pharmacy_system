package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment,Long> {

    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.dermatologistAppointmentEndTime < CURRENT_TIMESTAMP ")
    Page<DermatologistAppointment> findAllPastDermatologistAppointment(Long id, Pageable pageable);

    @Query("select a from DermatologistAppointment  a where a.dermatologistForAppointment.id = ?1 and a.pharmacyForDermatologistAppointment.id = ?2 and a.dermatologistAppointmentEndTime < CURRENT_TIMESTAMP")
    Page<DermatologistAppointment> findAllPastDermatologistAppointmentByPharmacy(Long idDermatologist,Long idPharmacy,Pageable pageable);

    @Query(value = "select d  from DermatologistAppointment as d where d.statusOfAppointment = 0 and d.dermatologistAppointmentStartTime > CURRENT_DATE")
    List<DermatologistAppointment> getOpenDermatologistAppointment();
}

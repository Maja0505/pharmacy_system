package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IPharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment,Long> {

    @Query("SELECT a FROM PharmacistAppointment a WHERE a.pharmacistForAppointment.id = ?1 and (a.statusOfAppointment = 2 or a.statusOfAppointment = 3)")
    List<PharmacistAppointment> findAllPastPharmacistAppointment(Long id);

    //Nemanja
    @Query("select a from PharmacistAppointment a where a.pharmacistForAppointment.id = ?1 and a.statusOfAppointment = 2")
    List<PharmacistAppointment> findAllMissedPharmacistAppointmentByPharmacist(Long id);

    //Nemanja
    @Query("select a from PharmacistAppointment a where a.pharmacistForAppointment.id = ?1 and a.statusOfAppointment = 3")
    List<PharmacistAppointment> findAllExpiredPharmacistAppointmentByPharmacist(Long id);

    //Nemanja
    @Query("select a from PharmacistAppointment a where a.pharmacistForAppointment.id = ?1 and a.statusOfAppointment = 1")
    List<PharmacistAppointment> findAllReservedPharmacistAppointmentByPharmacist(Long id);

    //Nemanja
    @Query("select a from PharmacistAppointment a where a.pharmacistForAppointment.id = ?1 and a.statusOfAppointment = 1 and a.pharmacistAppointmentStartTime > current_timestamp")
    List<PharmacistAppointment> findAllFutureReservedPharmacistAppointmentByPharmacist(Long id);


}

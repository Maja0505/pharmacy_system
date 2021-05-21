package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment,Long> {

    @Query(value = "select d  from DermatologistAppointment as d where d.statusOfAppointment = 0 and d.pharmacyForDermatologistAppointment.id = ?1 and d.dermatologistAppointmentStartTime > CURRENT_DATE")
    List<DermatologistAppointment> getOpenDermatologistAppointment(Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and (a.statusOfAppointment = 2 or a.statusOfAppointment = 3) ")
    Page<DermatologistAppointment> findAllPastDermatologistAppointment(Long id, Pageable pageable);

    //Nemanja
    @Query("select a from DermatologistAppointment  a where a.dermatologistForAppointment.id = ?1 and a.pharmacyForDermatologistAppointment.id = ?2 and (a.statusOfAppointment = 2 or a.statusOfAppointment = 3)")
    Page<DermatologistAppointment> findAllPastDermatologistAppointmentByPharmacy(Long idDermatologist,Long idPharmacy,Pageable pageable);

    @Query(value = "select d  from DermatologistAppointment as d where d.statusOfAppointment = 0 and d.dermatologistAppointmentStartTime > CURRENT_DATE")
    List<DermatologistAppointment> getOpenDermatologistAppointment();

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.statusOfAppointment = 0 and a.pharmacyForDermatologistAppointment.id = ?2 and a.dermatologistAppointmentStartTime > CURRENT_TIMESTAMP")
    List<DermatologistAppointment> findAllFutureOpenDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.statusOfAppointment = 1 and a.pharmacyForDermatologistAppointment.id = ?2 and a.dermatologistAppointmentEndTime > CURRENT_TIMESTAMP")
    List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.pharmacyForDermatologistAppointment.id = ?2 and a.statusOfAppointment=2")
    List<DermatologistAppointment> findAllMissedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.pharmacyForDermatologistAppointment.id = ?2 and a.statusOfAppointment=3")
    List<DermatologistAppointment> findAllExpiredDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.pharmacyForDermatologistAppointment.id = ?2 and a.statusOfAppointment=1")
    List<DermatologistAppointment> findAllReservedDermatologistAppointmentByDermatologistAndPharmacy(Long dermatologistId,Long pharmacyId);

    //Nemanja
    @Query("select a from DermatologistAppointment a where a.dermatologistForAppointment.id = ?1 and a.statusOfAppointment = 1 and a.dermatologistAppointmentEndTime > CURRENT_TIMESTAMP")
    List<DermatologistAppointment> findAllFutureReservedDermatologistAppointmentByDermatologist(Long dermatologistId);


}

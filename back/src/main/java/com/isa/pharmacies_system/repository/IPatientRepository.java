package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p.dermatologistAppointment from Patient p where p.id=?1 ")
    Page<DermatologistAppointment> getAllByDermatologistAppointment(Long id,Pageable pagable);

    @Query("select p.pharmacistAppointments from Patient p where p.id=?1 ")
    Page<PharmacistAppointment> getAllByPharmacistAppointment(Long id, Pageable pagable);

    @Query("select p.patientMedicineReservations from Patient p where p.id=?1 ")
    Page<MedicineReservation> getAllByMedicineReservations(Long id, Pageable pagable);
}

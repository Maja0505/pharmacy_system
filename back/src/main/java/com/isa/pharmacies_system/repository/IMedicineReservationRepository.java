package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {

    //Nemanja
    @Query("select mr from MedicineReservation as mr where mr.id=?1 and mr.pharmacyForMedicineReservation.id=?2")
    List<MedicineReservation> findMedicineReservationByIdAndByPharmacy(Long medicineReservationId,Long pharmacyId);
}

package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPharmacistReportRepository extends JpaRepository<PharmacistReport,Long> {

    @Query("select p from PharmacistReport p where p.pharmacistAppointment.patientWithPharmacistAppointment.id = ?1")
    List<PharmacistReport> getAllPharmacistReportForPatient(Long id, Pageable pageable);
}

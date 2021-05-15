package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.report.DermatologistReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IDermatologistReportRepository extends JpaRepository<DermatologistReport,Long> {

    @Query("select d from DermatologistReport d where d.dermatologistAppointment.patientWithDermatologistAppointment.id = ?1")
    List<DermatologistReport> getAllDermatologistReportForPatient(Long id, Pageable pageable);
}

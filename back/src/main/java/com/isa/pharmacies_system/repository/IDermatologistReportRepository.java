package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.report.DermatologistReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDermatologistReportRepository extends JpaRepository<DermatologistReport,Long> {
}

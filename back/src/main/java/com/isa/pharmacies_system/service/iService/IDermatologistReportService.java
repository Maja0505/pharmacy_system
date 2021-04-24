package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.report.DermatologistReport;

import java.util.List;

public interface IDermatologistReportService {

    List<DermatologistReport> findAll();
    Boolean createDermatologistReport(DermatologistReport dermatologistReport,Long appointmentId);
}

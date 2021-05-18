package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.report.PharmacistReport;

import java.util.List;

public interface IPharmacistReportService {

    List<PharmacistReport> findAllPharmacistReportForPatient(Long id, int page);
}

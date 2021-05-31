package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.domain.report.DermatologistReport;

import java.util.List;

public interface IDermatologistReportService {

    List<DermatologistReport> findAll();
    Boolean createDermatologistReport(DermatologistReport dermatologistReport,Long appointmentId);
    List<DermatologistReport> findAllDermatologistReportForPatient(Long id);
    List<DermatologistReportDTO> sortByPharmacyDate(List<DermatologistReportDTO> dermatologistReports, boolean asc);
    List<DermatologistReportDTO>  sortByPharmacyDuration(List<DermatologistReportDTO> dermatologistReports, boolean asc);
    List<DermatologistReportDTO> sortByPharmacyPrice(List<DermatologistReportDTO> dermatologistReports, boolean asc);
}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.domain.report.PharmacistReport;

import java.util.List;

public interface IPharmacistReportService {

    List<PharmacistReport> findAllPharmacistReportForPatient(Long id);
    Boolean createPharmacistReport(PharmacistReport pharmacistReport, Long appointmentId);

    List<PharmacistReportDTO> sortByPharmacyDate(List<PharmacistReportDTO> pharmacistReports, boolean asc);

    List<PharmacistReportDTO> sortByPharmacyDuration(List<PharmacistReportDTO> pharmacistReports, boolean asc);

    List<PharmacistReportDTO> sortByPharmacyPrice(List<PharmacistReportDTO> pharmacistReports, boolean asc);
}

package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.repository.IPharmacistReportRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistReportService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistReportService implements IPharmacistReportService {

    private IPharmacistReportRepository pharmacistReportRepository;

    public PharmacistReportService(IPharmacistReportRepository pharmacistReportRepository) {
        this.pharmacistReportRepository = pharmacistReportRepository;
    }

    //#1
    @Override
    public List<PharmacistReport> findAllPharmacistReportForPatient(Long id, int page) {
        return pharmacistReportRepository.getAllPharmacistReportForPatient(id, PageRequest.of(page,10));
    }
}

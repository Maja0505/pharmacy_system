package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IDermatologistReportRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DermatologistReportService implements IDermatologistReportService {

    private IDermatologistReportRepository dermatologistReportRepository;
    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;

    @Autowired
    public DermatologistReportService(IDermatologistReportRepository dermatologistReportRepository, IDermatologistAppointmentRepository dermatologistAppointmentRepository) {
        this.dermatologistReportRepository = dermatologistReportRepository;
        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
    }

    //Nemanja
    @Override
    public List<DermatologistReport> findAll() {
        return dermatologistReportRepository.findAll();
    }

    //Nemanja
    @Override
    public Boolean createDermatologistReport(DermatologistReport dermatologistReport,Long appointmentId) {
        DermatologistAppointment dermatologistAppointment = dermatologistAppointmentRepository.findById(appointmentId).orElse(null);
        if(dermatologistAppointment != null){
            dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Expired);
            dermatologistReport.setDermatologistAppointment(dermatologistAppointment);
            Recipe recipe = dermatologistReport.getRecipeForDermatologistReport();
            recipe.setPatientForRecipe(dermatologistAppointment.getPatientWithDermatologistAppointment());
            dermatologistReportRepository.save(dermatologistReport);
            return true;
        }
        return false;
    }

    //#1
    @Override
    public List<DermatologistReport> findAllDermatologistReportForPatient(Long id) {
        return dermatologistReportRepository.getAllDermatologistReportForPatient(id);
    }

    @Override
    public List<DermatologistReportDTO> sortByPharmacyDate(List<DermatologistReportDTO> dermatologistReports, boolean asc) {
        if(asc){
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getDermatologistAppointmentStartTime));
        }else{
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getDermatologistAppointmentStartTime).reversed());
        }
        return dermatologistReports;
    }

    @Override
    public List<DermatologistReportDTO> sortByPharmacyDuration(List<DermatologistReportDTO> dermatologistReports, boolean asc) {
        if(asc){
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getDurationOfAppointment));
        }else{
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getDurationOfAppointment).reversed());
        }
        return dermatologistReports;
    }

    @Override
    public List<DermatologistReportDTO> sortByPharmacyPrice(List<DermatologistReportDTO> dermatologistReports, boolean asc) {
        if(asc){
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getAppointmentPrice));
        }else{
            Collections.sort(dermatologistReports, Comparator.comparing(DermatologistReportDTO::getAppointmentPrice).reversed());
        }
        return dermatologistReports;
    }
}

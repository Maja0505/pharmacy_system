package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistReportRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistReportService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PharmacistReportService implements IPharmacistReportService {

    private IPharmacistReportRepository pharmacistReportRepository;
    private IPharmacistAppointmentRepository pharmacistAppointmentRepository;

    public PharmacistReportService(IPharmacistReportRepository pharmacistReportRepository, IDermatologistAppointmentRepository dermatologistAppointmentRepository, IPharmacistAppointmentRepository pharmacistAppointmentRepository) {
        this.pharmacistReportRepository = pharmacistReportRepository;
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
    }

    //#1
    @Override
    public List<PharmacistReport> findAllPharmacistReportForPatient(Long id) {
        return pharmacistReportRepository.getAllPharmacistReportForPatient(id);
    }

    //Nemanja
    @Override
    public Boolean createPharmacistReport(PharmacistReport pharmacistReport, Long appointmentId) {
        PharmacistAppointment pharmacistAppointment = pharmacistAppointmentRepository.findById(appointmentId).orElse(null);
        if(pharmacistAppointment != null){
            pharmacistAppointment.setStatusOfAppointment(StatusOfAppointment.Expired);
            pharmacistReport.setPharmacistAppointment(pharmacistAppointment);
            Recipe recipe = pharmacistReport.getRecipeForPharmacistReport();
            recipe.setPatientForRecipe(pharmacistAppointment.getPatientWithPharmacistAppointment());
            pharmacistReportRepository.save(pharmacistReport);
            return true;
        }
        return false;


    }

    @Override
    public List<PharmacistReportDTO> sortByPharmacyDate(List<PharmacistReportDTO> pharmacistReports, boolean asc) {
        if(asc){
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getPharmacistAppointmentStartTime));
        }else{
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getPharmacistAppointmentStartTime).reversed());
        }
        return pharmacistReports;
    }

    @Override
    public List<PharmacistReportDTO> sortByPharmacyDuration(List<PharmacistReportDTO> pharmacistReports, boolean asc) {
        if(asc){
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getDuration));
        }else{
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getDuration).reversed());
        }
        return pharmacistReports;
    }

    @Override
    public List<PharmacistReportDTO> sortByPharmacyPrice(List<PharmacistReportDTO> pharmacistReports, boolean asc) {
        if(asc){
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getAppointmentPrice));
        }else{
            Collections.sort(pharmacistReports, Comparator.comparing(PharmacistReportDTO::getAppointmentPrice).reversed());
        }
        return pharmacistReports;
    }
}

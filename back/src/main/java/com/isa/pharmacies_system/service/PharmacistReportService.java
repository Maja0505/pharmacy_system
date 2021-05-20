package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistReportRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistReportService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public List<PharmacistReport> findAllPharmacistReportForPatient(Long id, int page) {
        return pharmacistReportRepository.getAllPharmacistReportForPatient(id, PageRequest.of(page,10));
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
}

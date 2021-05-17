package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import com.isa.pharmacies_system.repository.IWorkingHoursRepository;
import com.isa.pharmacies_system.service.iService.IWorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHoursService implements IWorkingHoursService {

    private IWorkingHoursRepository workingHoursRepository;

    @Autowired
    public WorkingHoursService(IWorkingHoursRepository workingHoursRepository) {
        this.workingHoursRepository = workingHoursRepository;
    }

    //Nemanja
    @Override
    public WorkingHours getWorkingHoursById(Long id) {
        return workingHoursRepository.findById(id).orElse(null);
    }

    //Nemanja
    @Override
    public List<WorkingHours> getAllFutureWorkingHoursForDermatologistInPharmacy(Long dermatologistId, Long pharmacyId) {
        return workingHoursRepository.findAllFutureWorkingHoursForDermatologistInPharmacy(dermatologistId,pharmacyId);
    }

    //Nemanja
    @Override
    public List<WorkingHours> getAllFutureWorkingHoursForPharmacist(Long pharmacistId) {
        return workingHoursRepository.findAllFutureWorkingHoursForPharmacist(pharmacistId);
    }
}

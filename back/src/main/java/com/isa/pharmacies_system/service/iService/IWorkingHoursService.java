package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.schedule.WorkingHours;

import java.util.List;

public interface IWorkingHoursService {

    WorkingHours getWorkingHoursById(Long id);
    List<WorkingHours> getAllFutureWorkingHoursForDermatologistInPharmacy(Long dermatologistId,Long pharmacyId);
}

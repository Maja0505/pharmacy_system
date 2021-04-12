package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.util.List;

public interface IPharmacistVacationRequestService {

    Boolean createPharmacistVacationRequest(PharmacistVacationRequest pharmacistVacationRequest,Long pharmacistId);
    Pharmacist findPharmacistForVacationRequest(Long id);
    List<PharmacistVacationRequest> getAllPharmacistVacationRequest();
    List<PharmacistVacationRequest> getAllPharmacistVacationRequestByPharmacistId(Long id);
    List<VacationRequest> makeVacationRequestListFromPharmacistRequestList(Long pharmacistId);
}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.domain.user.Dermatologist;

import java.util.List;

public interface IDermatologistVacationRequestService {

    Boolean createDermatologistVacationRequest(DermatologistVacationRequest dermatologistVacationRequest,Long dermatologistId);
    Dermatologist findDermatologistForVacationRequest(Long id);
    List<DermatologistVacationRequest> getAllDermatologistVacationRequest();
    List<DermatologistVacationRequest> getAllDermatologistVacationRequestByDermatologistId(Long id);
    List<VacationRequest> makeVacationRequestFromDermatologistRequest(Long dermatologistId);
}

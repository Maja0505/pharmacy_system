package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.schedule.VacationRequest;

import java.util.List;

public interface IVacationRequestService {

    Boolean checkVacationRequest(VacationRequest vacationRequest, List<VacationRequest> listVacationRequestsInDatabase);

}

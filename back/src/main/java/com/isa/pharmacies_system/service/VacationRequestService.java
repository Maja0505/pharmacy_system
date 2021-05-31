package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.repository.IVacationRequestRepository;
import com.isa.pharmacies_system.service.iService.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationRequestService implements IVacationRequestService {

    private IVacationRequestRepository vacationRequestRepository;

    @Autowired
    public VacationRequestService(IVacationRequestRepository vacationRequestRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
    }

    @Override
    public Boolean checkVacationRequest(VacationRequest vacationRequest,List<VacationRequest> listVacationRequestsInDatabase) {
        if(vacationRequest.getVacationStartDate().isBefore(LocalDate.now())){
           return false; 
        }
        if(vacationRequest.getVacationStartDate().isAfter(vacationRequest.getVacationEndDate())){
            return false;
        }
        for (VacationRequest request:listVacationRequestsInDatabase
        ) {
            if(vacationRequest.getVacationStartDate().isEqual(request.getVacationStartDate())
                    || vacationRequest.getVacationEndDate().isEqual(request.getVacationEndDate())){
                return false;
            }
            if(vacationRequest.getVacationStartDate().isAfter(request.getVacationStartDate())
                    && vacationRequest.getVacationStartDate().isBefore(request.getVacationEndDate())){
                return false;
            }
            if(vacationRequest.getVacationEndDate().isAfter(request.getVacationStartDate())
                    && vacationRequest.getVacationEndDate().isBefore(request.getVacationEndDate())){
                return false;
            }
            if(vacationRequest.getVacationStartDate().isBefore(request.getVacationStartDate())
                    && vacationRequest.getVacationEndDate().isAfter(request.getVacationEndDate())){
                return false;
            }
        }
        return true;
    }
}

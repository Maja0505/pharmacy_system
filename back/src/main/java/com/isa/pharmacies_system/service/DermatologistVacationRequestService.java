package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.TypeOfVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.repository.IDermatologistVacationRequestRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistService;
import com.isa.pharmacies_system.service.iService.IDermatologistVacationRequestService;
import com.isa.pharmacies_system.service.iService.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistVacationRequestService implements IDermatologistVacationRequestService {

    private IDermatologistVacationRequestRepository dermatologistVacationRequestRepository;
    private IDermatologistService dermatologistService;
    private IVacationRequestService vacationRequestService;

    @Autowired
    public DermatologistVacationRequestService(IDermatologistVacationRequestRepository dermatologistVacationRequestRepository, IDermatologistService dermatologistService, IVacationRequestService vacationRequestService) {
        this.dermatologistVacationRequestRepository = dermatologistVacationRequestRepository;
        this.dermatologistService = dermatologistService;
        this.vacationRequestService = vacationRequestService;
    }

    @Override
    public Boolean createDermatologistVacationRequest(DermatologistVacationRequest dermatologistVacationRequest, Long dermatologistId) {
        Dermatologist dermatologist = findDermatologistForVacationRequest(dermatologistId);
        dermatologistVacationRequest.setVacationRequestDermatologist(dermatologist);
        List<VacationRequest> list = makeVacationRequestFromDermatologistRequest(dermatologistId);
        if(dermatologist != null && vacationRequestService.checkVacationRequest(dermatologistVacationRequest,list)){
            dermatologistVacationRequestRepository.save(dermatologistVacationRequest);
            return true;
        }
        return false;
    }

    private List<VacationRequest> makeVacationRequestFromDermatologistRequest(Long dermatologistId){
        List<VacationRequest> list = new ArrayList<>();
        for (DermatologistVacationRequest p:getAllDermatologistVacationRequestByDermatologistId(dermatologistId)
        ) {
            list.add(p);
        }
        return list;
    }

    @Override
    public Dermatologist findDermatologistForVacationRequest(Long id) {
        return dermatologistService.getDermatologist(id);
    }

    @Override
    public List<DermatologistVacationRequest> getAllDermatologistVacationRequest() {
        return dermatologistVacationRequestRepository.findAll().stream()
                .filter(dermatologistVacationRequest -> dermatologistVacationRequest.getTypeOfVacationRequest().equals(TypeOfVacationRequest.Dermatologist_vacation_request))
                .collect(Collectors.toList());
    }

    @Override
    public List<DermatologistVacationRequest> getAllDermatologistVacationRequestByDermatologistId(Long id) {
        return dermatologistVacationRequestRepository.findAllByVacationRequestDermatologistId(id);
    }


}

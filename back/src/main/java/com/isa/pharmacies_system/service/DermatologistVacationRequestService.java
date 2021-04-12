package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.TypeOfVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.repository.IDermatologistRepository;
import com.isa.pharmacies_system.repository.IDermatologistVacationRequestRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistVacationRequestService implements IDermatologistVacationRequestService {

    private IDermatologistVacationRequestRepository dermatologistVacationRequestRepository;
    private IDermatologistRepository dermatologistRepository;

    @Autowired
    public DermatologistVacationRequestService(IDermatologistVacationRequestRepository dermatologistVacationRequestRepository, IDermatologistRepository dermatologistRepository) {
        this.dermatologistVacationRequestRepository = dermatologistVacationRequestRepository;
        this.dermatologistRepository = dermatologistRepository;
    }

    @Override
    public Boolean createDermatologistVacationRequest(DermatologistVacationRequest dermatologistVacationRequest, Long dermatologistId) {
        Dermatologist dermatologist = findDermatologistForVacationRequest(dermatologistId);
        dermatologistVacationRequest.setVacationRequestDermatologist(dermatologist);
        if(dermatologist != null){
            dermatologistVacationRequestRepository.save(dermatologistVacationRequest);
            return true;
        }
        return false;
    }

    @Override
    public List<VacationRequest> makeVacationRequestFromDermatologistRequest(Long dermatologistId){
        List<VacationRequest> list = new ArrayList<>();
        for (DermatologistVacationRequest p:getAllDermatologistVacationRequestByDermatologistId(dermatologistId)
        ) {
            list.add(p);
        }
        return list;
    }


    @Override
    public Dermatologist findDermatologistForVacationRequest(Long id) {
        return dermatologistRepository.findById(id).orElse(null);
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

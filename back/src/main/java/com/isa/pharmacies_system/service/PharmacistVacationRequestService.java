package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.TypeOfVacationRequest;
import com.isa.pharmacies_system.domain.schedule.VacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IPharmacistRepository;
import com.isa.pharmacies_system.repository.IPharmacistVacationRequestRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistVacationRequestService implements IPharmacistVacationRequestService {

    private IPharmacistVacationRequestRepository pharmacistVacationRequestRepository;
    private IPharmacistRepository pharmacistRepository;

    @Autowired
    public PharmacistVacationRequestService(IPharmacistVacationRequestRepository pharmacistVacationRequestRepository, IPharmacistRepository pharmacistRepository) {
        this.pharmacistVacationRequestRepository = pharmacistVacationRequestRepository;
        this.pharmacistRepository = pharmacistRepository;
    }


    @Override
    public Boolean createPharmacistVacationRequest(PharmacistVacationRequest pharmacistVacationRequest,Long pharmacistId) {
        Pharmacist pharmacist = findPharmacistForVacationRequest(pharmacistId);
        pharmacistVacationRequest.setVacationRequestPharmacist(pharmacist);
        List<VacationRequest> list = makeVacationRequestListFromPharmacistRequestList(pharmacistId);
        if(pharmacist != null){
            pharmacistVacationRequestRepository.save(pharmacistVacationRequest);
            return true;
        }
        return false;
    }

    @Override
    public List<VacationRequest> makeVacationRequestListFromPharmacistRequestList(Long pharmacistId){
        List<VacationRequest> list = new ArrayList<>();
        for (PharmacistVacationRequest p:getAllPharmacistVacationRequestByPharmacistId(pharmacistId)
        ) {
            list.add(p);
        }
        return list;
    }

    @Override
    public Pharmacist findPharmacistForVacationRequest(Long id) {
        return pharmacistRepository.findById(id).orElse(null);
    }

    @Override
    public List<PharmacistVacationRequest> getAllPharmacistVacationRequest() {
        return pharmacistVacationRequestRepository.findAll().stream()
                .filter(pharmacistVacationRequest -> pharmacistVacationRequest.getTypeOfVacationRequest().equals(TypeOfVacationRequest.Pharmacist_vacation_request))
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacistVacationRequest> getAllPharmacistVacationRequestByPharmacistId(Long id) {
        return pharmacistVacationRequestRepository.findAllByVacationRequestPharmacistId(id);
    }


}

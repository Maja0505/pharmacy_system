package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.IPharmacistVacationRequestRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistService;
import com.isa.pharmacies_system.service.iService.IPharmacistVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistVacationRequestService implements IPharmacistVacationRequestService {

    private IPharmacistVacationRequestRepository pharmacistVacationRequestRepository;
    private IPharmacistService pharmacistService;

    @Autowired
    public PharmacistVacationRequestService(IPharmacistVacationRequestRepository pharmacistVacationRequestRepository, IPharmacistService pharmacistService) {
        this.pharmacistVacationRequestRepository = pharmacistVacationRequestRepository;
        this.pharmacistService = pharmacistService;
    }


    @Override
    public Boolean createPharmacistVacationRequest(PharmacistVacationRequest pharmacistVacationRequest,Long pharmacistId) {
        Pharmacist pharmacist = findPharmacistForVacationRequest(pharmacistId);
        pharmacistVacationRequest.setVacationRequestPharmacist(pharmacist);
        if(pharmacist != null && checkPharmacistVacationRequest(pharmacistVacationRequest)){
            pharmacistVacationRequestRepository.save(pharmacistVacationRequest);
            return true;
        }
        return false;
    }

    @Override
    public Pharmacist findPharmacistForVacationRequest(Long id) {
        return pharmacistService.getPharmacist(id);
    }

    @Override
    public List<PharmacistVacationRequest> getAllPharmacistVacationRequest() {
        return pharmacistVacationRequestRepository.findAll();
    }

    @Override
    public Boolean checkPharmacistVacationRequest(PharmacistVacationRequest pharmacistVacationRequest) {
        if(pharmacistVacationRequest.getVacationStartDate().isAfter(pharmacistVacationRequest.getVacationEndDate())){
            return false;
        }
        List<PharmacistVacationRequest> listPharmacistVacationRequestInDatabase = getAllPharmacistVacationRequest();
        for (PharmacistVacationRequest request:listPharmacistVacationRequestInDatabase
             ) {
            if(pharmacistVacationRequest.getVacationStartDate().isEqual(request.getVacationStartDate())
                || pharmacistVacationRequest.getVacationEndDate().isEqual(request.getVacationEndDate())){
                return false;
            }
            if(pharmacistVacationRequest.getVacationStartDate().isAfter(request.getVacationStartDate())
                    && pharmacistVacationRequest.getVacationStartDate().isBefore(request.getVacationEndDate())){
                return false;
            }
            if(pharmacistVacationRequest.getVacationEndDate().isAfter(request.getVacationStartDate())
                && pharmacistVacationRequest.getVacationEndDate().isBefore(request.getVacationEndDate())){
               return false;
            }
            if(pharmacistVacationRequest.getVacationStartDate().isBefore(request.getVacationStartDate())
               && pharmacistVacationRequest.getVacationEndDate().isAfter(request.getVacationEndDate())){
               return false;
            }
        }
        return true;
    }


}

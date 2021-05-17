package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.repository.IEPrescriptionRepository;
import com.isa.pharmacies_system.service.iService.IEPrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EPrescriptionService implements IEPrescriptionService {

    private IEPrescriptionRepository ePrescriptionRepository;

    public EPrescriptionService(IEPrescriptionRepository eIePrescriptionRepository) {
        this.ePrescriptionRepository = eIePrescriptionRepository;
    }

    @Override
    public List<EPrescriptionItem> getAllEPrescriptionItemForEPrescription(Long ePrescriptionId){
        return ePrescriptionRepository.getAllEPrescriptionItemForEPrescription(ePrescriptionId);
    }
}

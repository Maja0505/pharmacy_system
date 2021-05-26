package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.EPrescriptionDTO;
import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.repository.IEPrescriptionRepository;
import com.isa.pharmacies_system.service.iService.IEPrescriptionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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

    @Override
    public List<EPrescriptionDTO> sortByEPrescriptionByDate(List<EPrescriptionDTO> ePrescriptions, boolean asc) {
        if(asc){
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getLocalDateTime));
        }else{
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getLocalDateTime).reversed());
        }
        return ePrescriptions;
    }

    @Override
    public List<EPrescriptionDTO> sortByEPrescriptionByStatus(List<EPrescriptionDTO> ePrescriptions, boolean asc) {
        if(asc){
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getStatusOfEPrescription));
        }else{
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getStatusOfEPrescription).reversed());
        }
        return ePrescriptions;
    }
}

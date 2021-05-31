package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.EPrescriptionDTO;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;

import java.util.List;

public interface IEPrescriptionService {
    List<EPrescriptionItem> getAllEPrescriptionItemForEPrescription(Long ePrescriptionId);

    List<EPrescriptionDTO> sortByEPrescriptionByDate(List<EPrescriptionDTO> ePrescriptions, boolean asc);

    List<EPrescriptionDTO> sortByEPrescriptionByStatus(List<EPrescriptionDTO> ePrescriptions, boolean asc);
}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;

import java.util.List;

public interface IEPrescriptionService {
    List<EPrescriptionItem> getAllEPrescriptionItemForEPrescription(Long ePrescriptionId);
}

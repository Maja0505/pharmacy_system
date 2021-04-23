package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.medicine.MedicineRequest;

import java.util.List;

public interface IMedicineRequestService {

    void createMedicineRequest(MedicineRequest medicineRequest);
    List<MedicineRequest> getAllMedicineRequest();
}

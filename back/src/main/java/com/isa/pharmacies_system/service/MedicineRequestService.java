package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.domain.medicine.MedicineRequestItem;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IMedicineRequestRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.service.iService.IMedicineRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineRequestService implements IMedicineRequestService {

    private IMedicineRequestRepository medicineRequestRepository;
    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;

    @Autowired
    public MedicineRequestService(IMedicineRequestRepository medicineRequestRepository, IPharmacyStorageItemRepository pharmacyStorageItemRepository) {
        this.medicineRequestRepository = medicineRequestRepository;
        this.pharmacyStorageItemRepository = pharmacyStorageItemRepository;
    }

    //Nemanja
    @Override
    public void createMedicineRequest(MedicineRequest medicineRequest) {
        medicineRequestRepository.save(medicineRequest);
    }

    //Nemanja
    @Override
    public List<MedicineRequest> getAllMedicineRequest() {
        return medicineRequestRepository.findAll();
    }
}

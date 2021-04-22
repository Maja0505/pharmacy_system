package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IMedicineReservationRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.service.iService.IMedicineReservationService;
import org.springframework.stereotype.Service;


@Service
public class MedicineReservationService implements IMedicineReservationService {

    private IMedicineReservationRepository medicineReservationRepository;
    private IPharmacyStorageItemRepository pharmacyStorageItemRepository;

    public MedicineReservationService(IMedicineReservationRepository medicineReservationRepository, IPharmacyStorageItemRepository pharmacyStorageItemRepository) {
        this.medicineReservationRepository = medicineReservationRepository;
        this.pharmacyStorageItemRepository = pharmacyStorageItemRepository;
    }

    //#1[3.19]
    @Override
    public Boolean createMedicineReservation(MedicineReservation medicineReservation){
        //uraditi provere
        PharmacyStorageItem pharmacyStorageItem = pharmacyStorageItemRepository.getSelectedMedicineFromPharmacyStorage(medicineReservation.getReservedMedicine().getId(),medicineReservation.getPharmacyForMedicineReservation().getId());
        if(pharmacyStorageItem != null && doesPharmacyHaveSelectedMedicineInStorage(pharmacyStorageItem)){
            medicineReservationRepository.save(medicineReservation);
            pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount() - 1);
            pharmacyStorageItemRepository.save(pharmacyStorageItem);
            return true;
        }
        return false;
    }

    private Boolean doesPharmacyHaveSelectedMedicineInStorage(PharmacyStorageItem pharmacyStorageItem){
        return pharmacyStorageItem.getMedicineAmount() > 0;
    }
}

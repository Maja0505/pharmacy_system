package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.domain.medicine.MedicineRequest;
import com.isa.pharmacies_system.domain.medicine.MedicineRequestItem;
import com.isa.pharmacies_system.domain.medicine.StateOfMedicineRequest;
import com.isa.pharmacies_system.domain.medicine.TypeOfItem;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;

public class MedicineRequestConverter {


    public MedicineRequest convertPharmacyStorageItemToMedicineRequest(PharmacyStorageItem pharmacyStorageItemWithNotEnoughAmount) {
        MedicineRequest medicineRequest = new MedicineRequest();
        MedicineRequestItem medicineRequestItem = new MedicineRequestItem();
        medicineRequestItem.setMedicineItem(pharmacyStorageItemWithNotEnoughAmount.getMedicineItem());
        medicineRequestItem.setTypeOfItem(TypeOfItem.Medicine_request_item);
        medicineRequestItem.setMedicineAmount(100);
        medicineRequest.setPharmacy(pharmacyStorageItemWithNotEnoughAmount.getPharmacyStorageWithItem().getPharmacy());
        medicineRequest.setStateOfMedicineRequest(StateOfMedicineRequest.Unavailable_Medicine);
        medicineRequest.setMedicineRequestItem(medicineRequestItem);
        return  medicineRequest;
    }
}

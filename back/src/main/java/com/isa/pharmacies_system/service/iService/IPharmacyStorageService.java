package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public interface IPharmacyStorageService {
	boolean checkMedicineHaveInPharmacy(Pharmacy pharmacy, Medicine medicine);
}

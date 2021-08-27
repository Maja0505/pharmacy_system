package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public interface IPriceListService {
    PriceListForAppointmentDTO findPriceListByPharmacyId(Long pharmacyId);
    PharmacyDTO addPriceListToPharmacyDTO(PharmacyDTO pharmacyDTO);
    MedicinePrice getPriceForMedicineInPharmacy(Medicine medicine, Pharmacy pharmacy);
}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;

public interface IPriceListService {
    PriceListForAppointmentDTO findPriceListByPharmacyId(Long pharmacyId);
    PharmacyDTO addPriceListToPharmacyDTO(PharmacyDTO pharmacyDTO);
}

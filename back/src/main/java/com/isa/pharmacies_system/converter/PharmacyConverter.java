package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;

public class PharmacyConverter {

    public PharmacyConverter() {
    }

    public PharmacyDTO convertPharmacyToPharmacyDTO(Pharmacy pharmacy){

        PharmacyDTO pharmacyDTO = new PharmacyDTO();
        pharmacyDTO.setId(pharmacy.getId());
        pharmacyDTO.setPharmacyName(pharmacy.getPharmacyName());
        pharmacyDTO.setPharmacyAddress(pharmacy.getPharmacyAddress());
        return pharmacyDTO;
    }
}

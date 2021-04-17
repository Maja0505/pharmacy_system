package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PharmacistInfoDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.util.ArrayList;
import java.util.List;

//#1
public class PharmacistConverter {

    public PharmacistConverter() {
    }

    public PharmacistInfoDTO convertPharmacistToPharmacistInfoDTO(Pharmacist pharmacist){
        PharmacistInfoDTO pharmacistInfoDTO = new PharmacistInfoDTO();
        pharmacistInfoDTO.setId(pharmacist.getId());
        pharmacistInfoDTO.setFirstName(pharmacist.getFirstName());
        pharmacistInfoDTO.setLastName(pharmacist.getLastName());
        pharmacistInfoDTO.setRating(pharmacist.getPharmacistAverageRating());
        return pharmacistInfoDTO;
    }

    public List<PharmacistInfoDTO> convertPharmacistListToPharmacistInfoDTOList(List<Pharmacist> pharmacists){
        List<PharmacistInfoDTO> pharmacistInfoDTOS = new ArrayList<>();
        for (Pharmacist pharmacist:pharmacists) {
            pharmacistInfoDTOS.add(convertPharmacistToPharmacistInfoDTO(pharmacist));
        }

        return pharmacistInfoDTOS;
    }
}

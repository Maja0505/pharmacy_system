package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PharmacistPersonalInfoDTO;
import com.isa.pharmacies_system.domain.user.Pharmacist;


public class PharmacistConverter {

    public PharmacistConverter() {
    }

    public PharmacistPersonalInfoDTO convertPersonalInfoToDTO(Pharmacist pharmacist) {

        PharmacistPersonalInfoDTO pharmacistPersonalInfoDTO = new PharmacistPersonalInfoDTO();
        pharmacistPersonalInfoDTO.setFirstName(pharmacist.getFirstName());
        pharmacistPersonalInfoDTO.setLastName(pharmacist.getLastName());
        pharmacistPersonalInfoDTO.setAddress(pharmacist.getUserAddress());
        pharmacistPersonalInfoDTO.setId(pharmacist.getId());
        pharmacistPersonalInfoDTO.setEmail(pharmacist.getEmail());
        pharmacistPersonalInfoDTO.setPhoneNumber(pharmacist.getPhoneNumber());
        pharmacistPersonalInfoDTO.setPassword(pharmacist.getPassword());

        return pharmacistPersonalInfoDTO;
    }

    public Pharmacist convertDTOToPersonalInfo(PharmacistPersonalInfoDTO pharmacistPersonalInfoDTO,Pharmacist pharmacist){
        pharmacist.setFirstName(pharmacistPersonalInfoDTO.getFirstName());
        pharmacist.setLastName(pharmacistPersonalInfoDTO.getLastName());
        pharmacist.setUserAddress(pharmacistPersonalInfoDTO.getAddress());
        pharmacist.setPhoneNumber(pharmacistPersonalInfoDTO.getPhoneNumber());
        pharmacist.setEmail(pharmacistPersonalInfoDTO.getEmail());
        return pharmacist;
    }

}

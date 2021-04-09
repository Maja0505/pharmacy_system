package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.DermatologistPersonalInfoDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;

public class DermatologistConverter {

    public DermatologistConverter() {
    }

    public DermatologistPersonalInfoDTO convertPersonalInfoToDTO(Dermatologist dermatologist) {
        DermatologistPersonalInfoDTO dermatologistPersonalInfoDTO = new DermatologistPersonalInfoDTO();
        dermatologistPersonalInfoDTO.setFirstName(dermatologist.getFirstName());
        dermatologistPersonalInfoDTO.setLastName(dermatologist.getLastName());
        dermatologistPersonalInfoDTO.setAddress(dermatologist.getUserAddress());
        dermatologistPersonalInfoDTO.setId(dermatologist.getId());
        dermatologistPersonalInfoDTO.setEmail(dermatologist.getEmail());
        dermatologistPersonalInfoDTO.setPhoneNumber(dermatologist.getPhoneNumber());
        dermatologistPersonalInfoDTO.setPassword(dermatologist.getPassword());

        return dermatologistPersonalInfoDTO;

    }

    public Dermatologist convertDTOToPersonalInfo(DermatologistPersonalInfoDTO dermatologistPersonalInfoDTO, Dermatologist dermatologist) {
        dermatologist.setFirstName(dermatologistPersonalInfoDTO.getFirstName());
        dermatologist.setLastName(dermatologistPersonalInfoDTO.getLastName());
        dermatologist.setUserAddress(dermatologistPersonalInfoDTO.getAddress());
        dermatologist.setPhoneNumber(dermatologistPersonalInfoDTO.getPhoneNumber());
        dermatologist.setEmail(dermatologistPersonalInfoDTO.getEmail());
        return dermatologist;
    }
}

package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.StaffPersonalInfoDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.domain.user.Users;

public class StaffConverter {

    public StaffConverter() {
    }

    public StaffPersonalInfoDTO convertDermatologistPersonalInfoToDTO(Dermatologist dermatologist) {
        return convertPersonalInfoToDTO(dermatologist);
    }

    public Dermatologist convertDTOToDermatologistPersonalInfo(StaffPersonalInfoDTO dermatologistPersonalInfoDTO, Dermatologist dermatologist) {
        return (Dermatologist) convertDTOToPersonalInfo(dermatologistPersonalInfoDTO,dermatologist);
    }

    public StaffPersonalInfoDTO convertPharmacistPersonalInfoToDTO(Pharmacist pharmacist) {
        return convertPersonalInfoToDTO(pharmacist);
    }

    public Pharmacist convertDTOToPharmacistPersonalInfo(StaffPersonalInfoDTO pharmacistPersonalInfoDTO, Pharmacist pharmacist) {
        return (Pharmacist) convertDTOToPersonalInfo(pharmacistPersonalInfoDTO,pharmacist);
    }

    private StaffPersonalInfoDTO convertPersonalInfoToDTO (Users user){
        StaffPersonalInfoDTO staffPersonalInfoDTO = new StaffPersonalInfoDTO();

        staffPersonalInfoDTO.setFirstName(user.getFirstName());
        staffPersonalInfoDTO.setLastName(user.getLastName());
        staffPersonalInfoDTO.setAddress(user.getUserAddress());
        staffPersonalInfoDTO.setId(user.getId());
        staffPersonalInfoDTO.setEmail(user.getEmail());
        staffPersonalInfoDTO.setPhoneNumber(user.getPhoneNumber());
        staffPersonalInfoDTO.setPassword(user.getPassword());

        return staffPersonalInfoDTO;
    }

    private Users convertDTOToPersonalInfo(StaffPersonalInfoDTO staffPersonalInfoDTO,Users user){
        user.setFirstName(staffPersonalInfoDTO.getFirstName());
        user.setLastName(staffPersonalInfoDTO.getLastName());
        user.setUserAddress(staffPersonalInfoDTO.getAddress());
        user.setPhoneNumber(staffPersonalInfoDTO.getPhoneNumber());
        user.setEmail(staffPersonalInfoDTO.getEmail());
        return user;
    }
}

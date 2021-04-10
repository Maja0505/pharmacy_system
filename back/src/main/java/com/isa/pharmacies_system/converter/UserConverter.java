package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.domain.user.Users;

public class UserConverter {

    public UserConverter() {
    }

    public UserPersonalInfoDTO convertDermatologistPersonalInfoToDTO(Dermatologist dermatologist) {
        return convertPersonalInfoToDTO(dermatologist);
    }

    public Dermatologist convertDTOToDermatologistPersonalInfo(UserPersonalInfoDTO dermatologistPersonalInfoDTO, Dermatologist dermatologist) {
        return (Dermatologist) convertDTOToPersonalInfo(dermatologistPersonalInfoDTO,dermatologist);
    }

    public UserPersonalInfoDTO convertPharmacistPersonalInfoToDTO(Pharmacist pharmacist) {
        return convertPersonalInfoToDTO(pharmacist);
    }

    public Pharmacist convertDTOToPharmacistPersonalInfo(UserPersonalInfoDTO pharmacistPersonalInfoDTO, Pharmacist pharmacist) {
        return (Pharmacist) convertDTOToPersonalInfo(pharmacistPersonalInfoDTO,pharmacist);
    }

    public UserPersonalInfoDTO convertPatientPersonalInfoToDTO(Patient patient){
        return convertPersonalInfoToDTO(patient);
    }

    public Patient convertDTOToPatientPersonalInfo(UserPersonalInfoDTO userPersonalInfoDTO, Patient patient){
        return (Patient) convertDTOToPersonalInfo(userPersonalInfoDTO,patient);
    }

    private UserPersonalInfoDTO convertPersonalInfoToDTO (Users user){
        UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO();

        userPersonalInfoDTO.setFirstName(user.getFirstName());
        userPersonalInfoDTO.setLastName(user.getLastName());
        userPersonalInfoDTO.setAddress(user.getUserAddress());
        userPersonalInfoDTO.setId(user.getId());
        userPersonalInfoDTO.setPhoneNumber(user.getPhoneNumber());
        userPersonalInfoDTO.setEmail(user.getEmail());
        return userPersonalInfoDTO;
    }

    private Users convertDTOToPersonalInfo(UserPersonalInfoDTO userPersonalInfoDTO, Users user){
        user.setFirstName(userPersonalInfoDTO.getFirstName());
        user.setLastName(userPersonalInfoDTO.getLastName());
        user.setUserAddress(userPersonalInfoDTO.getAddress());
        user.setPhoneNumber(userPersonalInfoDTO.getPhoneNumber());
        return user;
    }
}

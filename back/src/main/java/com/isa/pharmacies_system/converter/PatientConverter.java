package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PatientInfoDTO;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Users;

public class PatientConverter {

    public PatientConverter() {
    }

    public PatientInfoDTO convertPatientPersonalInfoToDTO(Patient patient){
        return convertPatientInfoToDTO(patient);
    }

    public Patient convertDTOToPatientPersonalInfo(PatientInfoDTO patientInfoDTO, Patient patient){
        return (Patient) convertDTOtoPersonalInfo(patientInfoDTO,patient);
    }
    public PatientInfoDTO convertPatientInfoToDTO(Users user){
        PatientInfoDTO patientInfoDTO = new PatientInfoDTO();

        patientInfoDTO.setId(user.getId());
        patientInfoDTO.setFirstName(user.getFirstName());
        patientInfoDTO.setLastName(user.getLastName());
        patientInfoDTO.setUserAddress(user.getUserAddress());
        patientInfoDTO.setPhoneNumber(user.getPhoneNumber());


        return patientInfoDTO;
    }

    public Users convertDTOtoPersonalInfo(PatientInfoDTO patientInfoDTO, Users user){

        user.setFirstName(patientInfoDTO.getFirstName());
        user.setLastName(patientInfoDTO.getLastName());
        user.setUserAddress(patientInfoDTO.getUserAddress());
        user.setPhoneNumber(patientInfoDTO.getPhoneNumber());

        return user;
    }
}

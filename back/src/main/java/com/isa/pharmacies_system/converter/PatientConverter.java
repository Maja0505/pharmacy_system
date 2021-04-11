package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PatientAdditionalInfoDTO;
import com.isa.pharmacies_system.domain.user.Patient;

public class PatientConverter {

    private  DermatologistAppointmentConverter dermatologistAppointmentConverter;
    public PatientConverter() {
        dermatologistAppointmentConverter = new DermatologistAppointmentConverter();
    }

    public PatientAdditionalInfoDTO convertPatientAdditionalInfoToDTO(Patient patient){

        PatientAdditionalInfoDTO patientAdditionalInfoDTO = new PatientAdditionalInfoDTO();
        patientAdditionalInfoDTO.setPatientPoints(patient.getPatientPoints());
        patientAdditionalInfoDTO.setCategoryOfPatient(patient.getCategoryOfPatient());
        return patientAdditionalInfoDTO;
    }
}

package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PatientAdditionalInfoDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PatientConverter{
    public PatientConverter() {
    }

    public PatientAdditionalInfoDTO convertPatientAdditionalInfoToDTO(Patient patient){

        PatientAdditionalInfoDTO patientAdditionalInfoDTO = new PatientAdditionalInfoDTO();
        patientAdditionalInfoDTO.setPatientPoints(patient.getPatientPoints());
        patientAdditionalInfoDTO.setCategoryOfPatient(patient.getCategoryOfPatient());

        return patientAdditionalInfoDTO;
    }

    public List<PatientAppointmentInfoDTO> convertPatientAppointmentInfoToDTO(Page<PharmacistAppointment> pharmacistAppointments){
        List<PatientAppointmentInfoDTO> list = new ArrayList<>();
        for (PharmacistAppointment appointment :pharmacistAppointments
             ) {
            list.add(makePatientAppointmentInfoDTO(appointment));
        }
        return list;
    }

    private PatientAppointmentInfoDTO makePatientAppointmentInfoDTO(PharmacistAppointment appointment){
        PatientAppointmentInfoDTO patientAppointmentInfoDTO = new PatientAppointmentInfoDTO();
        Patient patient = appointment.getPatientWithPharmacistAppointment();
        patientAppointmentInfoDTO.setPatientId(patient.getId());
        patientAppointmentInfoDTO.setPatientEmail(patient.getEmail());
        patientAppointmentInfoDTO.setPatientFirstName(patient.getFirstName());
        patientAppointmentInfoDTO.setPatientLastName(patient.getLastName());
        patientAppointmentInfoDTO.setAppointmentDuration(appointment.getPharmacistAppointmentDuration());
        patientAppointmentInfoDTO.setAppointmentPrice(appointment.getAppointmentPrice());
        patientAppointmentInfoDTO.setAppointmentStartTime(appointment.getPharmacistAppointmentStartTime());
        return patientAppointmentInfoDTO;
    }

}

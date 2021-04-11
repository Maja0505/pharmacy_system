package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PatientAdditionalInfoDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

public class PatientConverter{

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

    public List<PatientAppointmentInfoDTO> convertPatientPharmacistAppointmentInfoToDTO(Page<PharmacistAppointment> pharmacistAppointments){
        List<PatientAppointmentInfoDTO> list = new ArrayList<>();
        for (PharmacistAppointment appointment :pharmacistAppointments
             ) {
            list.add(makePharmacistAppointmentInfoDTO(appointment));
        }
        return list;
    }

    public List<PatientAppointmentInfoDTO> convertPatientDermatologistAppointmentInfoToDTO(Page<DermatologistAppointment> dermatologistAppointments){
        List<PatientAppointmentInfoDTO> list = new ArrayList<>();
        for (DermatologistAppointment appointment :dermatologistAppointments
        ) {
            list.add(makeDermatologistAppointmentInfoDTO(appointment));
        }
        return list;
    }

    private PatientAppointmentInfoDTO makeDermatologistAppointmentInfoDTO(DermatologistAppointment appointment){
        PatientAppointmentInfoDTO patientAppointmentInfoDTO = new PatientAppointmentInfoDTO();
        Patient patient = appointment.getPatientWithDermatologistAppointment();
        makePatientInfoDTO(patient,patientAppointmentInfoDTO);
        patientAppointmentInfoDTO.setAppointmentStartTime(appointment.getDermatologistAppointmentStartTime());
        patientAppointmentInfoDTO.setAppointmentEndTime(appointment.getDermatologistAppointmentEndTime());
        patientAppointmentInfoDTO.setAppointmentPrice(appointment.getAppointmentPrice());
        return patientAppointmentInfoDTO;
    }

    private PatientAppointmentInfoDTO makePharmacistAppointmentInfoDTO(PharmacistAppointment appointment){
        PatientAppointmentInfoDTO patientAppointmentInfoDTO = new PatientAppointmentInfoDTO();
        Patient patient = appointment.getPatientWithPharmacistAppointment();
        makePatientInfoDTO(patient,patientAppointmentInfoDTO);
        patientAppointmentInfoDTO.setAppointmentDuration(appointment.getPharmacistAppointmentDuration());
        patientAppointmentInfoDTO.setAppointmentPrice(appointment.getAppointmentPrice());
        patientAppointmentInfoDTO.setAppointmentStartTime(appointment.getPharmacistAppointmentStartTime());
        return patientAppointmentInfoDTO;
    }

    private void makePatientInfoDTO(Patient patient,PatientAppointmentInfoDTO patientAppointmentInfoDTO){
        patientAppointmentInfoDTO.setPatientId(patient.getId());
        patientAppointmentInfoDTO.setPatientEmail(patient.getEmail());
        patientAppointmentInfoDTO.setPatientFirstName(patient.getFirstName());
        patientAppointmentInfoDTO.setPatientLastName(patient.getLastName());
    }

}

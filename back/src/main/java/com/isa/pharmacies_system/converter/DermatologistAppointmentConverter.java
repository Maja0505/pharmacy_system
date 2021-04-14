package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfAppointment;

import java.util.ArrayList;
import java.util.List;

public class DermatologistAppointmentConverter {

    private UserConverter userConverter;
    private PharmacyConverter pharmacyConverter;

    public DermatologistAppointmentConverter() {
        this.userConverter = new UserConverter();
        this.pharmacyConverter = new PharmacyConverter();
    }

    public DermatologistAppointmentDTO convertDermatologistAppointmentToDermatologistAppointmentDTO(DermatologistAppointment dermatologistAppointment){

        DermatologistAppointmentDTO dermatologistAppointmentDTO = new DermatologistAppointmentDTO();
        dermatologistAppointmentDTO.setId(dermatologistAppointment.getId());
        dermatologistAppointmentDTO.setAppointmentPrice(dermatologistAppointment.getAppointmentPrice());
        dermatologistAppointmentDTO.setDermatologistAppointmentStartTime(dermatologistAppointment.getDermatologistAppointmentStartTime());
        dermatologistAppointmentDTO.setDermatologistAppointmentEndTime(dermatologistAppointment.getDermatologistAppointmentEndTime());
        dermatologistAppointmentDTO.setDermatologistForAppointment(userConverter.convertDermatologistPersonalInfoToDTO(dermatologistAppointment.getDermatologistForAppointment()));
        dermatologistAppointmentDTO.setPharmacyForDermatologistAppointment(pharmacyConverter.convertPharmacyToPharmacyDTO(dermatologistAppointment.getPharmacyForDermatologistAppointment()));
        return  dermatologistAppointmentDTO;
    }

    public List<DermatologistAppointmentDTO> convertListOfDermatologistAppointmentToDermatologistAppointmentDTOS(List<DermatologistAppointment> dermatologistAppointments){

        List<DermatologistAppointmentDTO> dermatologistAppointmentDTOS = new ArrayList<>();
        for (DermatologistAppointment d: dermatologistAppointments) {
            dermatologistAppointmentDTOS.add(convertDermatologistAppointmentToDermatologistAppointmentDTO(d));
        }
        return dermatologistAppointmentDTOS;
    }

    public DermatologistAppointment convertAppointmentScheduleByStaffDTOToDermatologistAppointment(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO){
        DermatologistAppointment dermatologistAppointment = new DermatologistAppointment();
        dermatologistAppointment.setDermatologistAppointmentStartTime(appointmentScheduleByStaffDTO.getAppointmentStartTime());
        dermatologistAppointment.setDermatologistAppointmentEndTime(appointmentScheduleByStaffDTO.getAppointmentEndTime());
        dermatologistAppointment.setAppointmentPoints(0);
        dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
        dermatologistAppointment.setTypeOfAppointment(TypeOfAppointment.Dermatologist_appointment);
        return dermatologistAppointment;
    }
}

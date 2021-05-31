package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.UtilityMethods;
import com.isa.pharmacies_system.service.iService.IPriceListService;

import java.util.ArrayList;
import java.util.List;

public class PharmacistAppointmentConverter {

    private UserConverter userConverter;
    private PharmacyConverter pharmacyConverter;
    private UtilityMethods utilityMethods;

    public PharmacistAppointmentConverter(IPriceListService priceListService) {
        this.userConverter = new UserConverter();
        this.pharmacyConverter = new PharmacyConverter(priceListService);
        this.utilityMethods = new UtilityMethods();
    }

    public PharmacistAppointmentDTO convertPharmacistAppointmentToDTO(PharmacistAppointment pharmacistAppointment){
        PharmacistAppointmentDTO pharmacistAppointmentDTO = new PharmacistAppointmentDTO();
        pharmacistAppointmentDTO.setId(pharmacistAppointment.getId());
        pharmacistAppointmentDTO.setAppointmentPrice(pharmacistAppointment.getAppointmentPrice());
        pharmacistAppointmentDTO.setPharmacistAppointmentStartTime(pharmacistAppointment.getPharmacistAppointmentStartTime());
        pharmacistAppointmentDTO.setPharmacistAppointmentDuration(pharmacistAppointment.getPharmacistAppointmentDuration());
        pharmacistAppointmentDTO.setPharmacistForAppointment(userConverter.convertPharmacistPersonalInfoToDTO(pharmacistAppointment.getPharmacistForAppointment()));
        pharmacistAppointmentDTO.setStartTime(pharmacistAppointment.getPharmacistAppointmentStartTime());
        pharmacistAppointmentDTO.setEndTime(pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes(pharmacistAppointment.getPharmacistAppointmentDuration()));
        try{
            PharmacyDTO pharmacyDTO = pharmacyConverter.convertPharmacyToPharmacyDTO(pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist());
            pharmacistAppointmentDTO.setPharmacyForPharmacistAppointment(pharmacyDTO);
            pharmacistAppointmentDTO.setPharmacyName(pharmacyDTO.getPharmacyName());
            pharmacistAppointmentDTO.setLocation(pharmacyDTO.getPharmacyAddress().getCity() + ", " + pharmacyDTO.getPharmacyAddress().getStreetName() + " " + pharmacyDTO.getPharmacyAddress().getStreetNumber());
            pharmacistAppointmentDTO.setPharmacyId(pharmacyDTO.getId());
        }catch (Exception e){
        }
        pharmacistAppointmentDTO.setPharmacistAppointmentEndTime(pharmacistAppointment.getPharmacistAppointmentStartTime().plusMinutes(pharmacistAppointment.getPharmacistAppointmentDuration()));
        Patient patient = pharmacistAppointment.getPatientWithPharmacistAppointment();
        utilityMethods.fillPatientInfoForPharmacistAppointment(patient,pharmacistAppointmentDTO);
        utilityMethods.setColorIdPharmacistAppointment(pharmacistAppointment,pharmacistAppointmentDTO);

        return pharmacistAppointmentDTO;

    }

    public List<PharmacistAppointmentDTO> convertPharmacistAppointmentsListToDTOS(List<PharmacistAppointment> pharmacistAppointments){
        List<PharmacistAppointmentDTO> pharmacistAppointmentDTOS = new ArrayList<>();
        for (PharmacistAppointment pharmacistAppointment: pharmacistAppointments) {
            pharmacistAppointmentDTOS.add(convertPharmacistAppointmentToDTO(pharmacistAppointment));
        }
        return pharmacistAppointmentDTOS;
    }

    public PharmacistAppointmentTimeDTO convertAppointmentScheduleByStaffDTOToPharmacistAppointmentTimeDTO(AppointmentScheduleByStaffDTO appointmentScheduleByStaffDTO) {
        PharmacistAppointmentTimeDTO pharmacistAppointmentTimeDTO = new PharmacistAppointmentTimeDTO();
        pharmacistAppointmentTimeDTO.setStartTime(appointmentScheduleByStaffDTO.getAppointmentStartTime());
        pharmacistAppointmentTimeDTO.setDuration((double) appointmentScheduleByStaffDTO.getAppointmentDuration());
        return pharmacistAppointmentTimeDTO;
    }
}

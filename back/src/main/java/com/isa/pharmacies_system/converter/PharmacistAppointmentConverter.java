package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.service.iService.IPriceListService;

import java.util.ArrayList;
import java.util.List;

public class PharmacistAppointmentConverter {

    private UserConverter userConverter;
    private PharmacyConverter pharmacyConverter;

    public PharmacistAppointmentConverter() {
        this.userConverter = new UserConverter();
        this.pharmacyConverter = new PharmacyConverter();
    }

    public PharmacistAppointmentConverter(IPriceListService priceListService) {
        this.userConverter = new UserConverter();
        this.pharmacyConverter = new PharmacyConverter(priceListService);
    }

    public PharmacistAppointmentDTO convertPharmacistAppointmentToDTO(PharmacistAppointment pharmacistAppointment){
        PharmacistAppointmentDTO pharmacistAppointmentDTO = new PharmacistAppointmentDTO();
        pharmacistAppointmentDTO.setId(pharmacistAppointment.getId());
        pharmacistAppointmentDTO.setAppointmentPrice(pharmacistAppointment.getAppointmentPrice());
        pharmacistAppointmentDTO.setPharmacistAppointmentStartTime(pharmacistAppointment.getPharmacistAppointmentStartTime());
        pharmacistAppointmentDTO.setPharmacistAppointmentDuration(pharmacistAppointment.getPharmacistAppointmentDuration());
        pharmacistAppointmentDTO.setPharmacistForAppointment(userConverter.convertPharmacistPersonalInfoToDTO(pharmacistAppointment.getPharmacistForAppointment()));
        pharmacistAppointmentDTO.setPharmacyForPharmacistAppointment(pharmacyConverter.convertPharmacyToPharmacyDTO(pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist()));
        return pharmacistAppointmentDTO;

    }

    public List<PharmacistAppointmentDTO> convertPharmacistAppointmentsListToDTOS(List<PharmacistAppointment> pharmacistAppointments){
        List<PharmacistAppointmentDTO> pharmacistAppointmentDTOS = new ArrayList<>();
        for (PharmacistAppointment pharmacistAppointment: pharmacistAppointments) {
            pharmacistAppointmentDTOS.add(convertPharmacistAppointmentToDTO(pharmacistAppointment));
        }
        return pharmacistAppointmentDTOS;
    }
}

package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.AppointmentScheduleByStaffDTO;
import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.UtilityMethods;
import com.isa.pharmacies_system.service.iService.IPriceListService;

import java.util.ArrayList;
import java.util.List;

public class DermatologistAppointmentConverter {

    private UserConverter userConverter;
    private PharmacyConverter pharmacyConverter;
    private IPriceListService priceListService;
    private UtilityMethods utilityMethods;

    public DermatologistAppointmentConverter() {
        this.userConverter = new UserConverter();
        this.pharmacyConverter = new PharmacyConverter();
        this.utilityMethods = new UtilityMethods();
    }
    public DermatologistAppointmentConverter(IPriceListService priceListService) {
        this.userConverter = new UserConverter();
        this.priceListService =  priceListService;
        this.pharmacyConverter = new PharmacyConverter(priceListService);
        this.utilityMethods = new UtilityMethods();
    }

    public DermatologistAppointmentDTO convertDermatologistAppointmentToDermatologistAppointmentDTO(DermatologistAppointment dermatologistAppointment){

        DermatologistAppointmentDTO dermatologistAppointmentDTO = new DermatologistAppointmentDTO();
        dermatologistAppointmentDTO.setId(dermatologistAppointment.getId());
        dermatologistAppointmentDTO.setAppointmentPrice(dermatologistAppointment.getAppointmentPrice());
        dermatologistAppointmentDTO.setDermatologistAppointmentStartTime(dermatologistAppointment.getDermatologistAppointmentStartTime());
        dermatologistAppointmentDTO.setDermatologistAppointmentEndTime(dermatologistAppointment.getDermatologistAppointmentEndTime());
        dermatologistAppointmentDTO.setDermatologistForAppointment(userConverter.convertDermatologistPersonalInfoToDTO(dermatologistAppointment.getDermatologistForAppointment()));
        PharmacyDTO pharmacyDTO = pharmacyConverter.convertPharmacyToPharmacyDTO(dermatologistAppointment.getPharmacyForDermatologistAppointment());
        dermatologistAppointmentDTO.setPharmacyForDermatologistAppointment(pharmacyDTO);
        dermatologistAppointmentDTO.setPharmacyName(pharmacyDTO.getPharmacyName());
        dermatologistAppointmentDTO.setLocation(pharmacyDTO.getPharmacyAddress().getCity() + ", " + pharmacyDTO.getPharmacyAddress().getStreetName() + " " + pharmacyDTO.getPharmacyAddress().getStreetNumber());

        Patient patient = dermatologistAppointment.getPatientWithDermatologistAppointment();
        utilityMethods.fillPatientInfoForDermatologistAppointment(patient,dermatologistAppointmentDTO);
        utilityMethods.setColorIdDermatologistAppointment(dermatologistAppointment,dermatologistAppointmentDTO);

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

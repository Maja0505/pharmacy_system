package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PharmacyInfoDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Dermatologist;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DermatologistReportConverter {

    public DermatologistReportConverter() {
    }

    public List<DermatologistReportDTO> convertDermatologistReportToDermatologistReportDTOS(List<DermatologistReport> dermatologistReports){
        List<DermatologistReportDTO> dermatologistReportDTOS = new ArrayList<>();
        for (DermatologistReport dermatologistReport:dermatologistReports) {
            dermatologistReportDTOS.add(convertDermatologistReportToDermatologistReportDTO(dermatologistReport));
        }
        return dermatologistReportDTOS;
    }

    public DermatologistReportDTO convertDermatologistReportToDermatologistReportDTO(DermatologistReport dermatologistReport){
        DermatologistReportDTO dermatologistReportDTO = new DermatologistReportDTO();
        dermatologistReportDTO.setId(dermatologistReport.getId());

        Dermatologist dermatologist = dermatologistReport.getDermatologistAppointment().getDermatologistForAppointment();
        dermatologistReportDTO.setDermatologistId(dermatologist.getId());
        dermatologistReportDTO.setDermatologistFirstName(dermatologist.getFirstName());
        dermatologistReportDTO.setDermatologistLastName(dermatologist.getLastName());

        Pharmacy pharmacy = dermatologistReport.getDermatologistAppointment().getPharmacyForDermatologistAppointment();
        dermatologistReportDTO.setPharmacyId(pharmacy.getId());
        dermatologistReportDTO.setPharmacyName(pharmacy.getPharmacyName());

        DermatologistAppointment dermatologistAppointment = dermatologistReport.getDermatologistAppointment();
        dermatologistReportDTO.setDermatologistAppointmentEndTime(dermatologistAppointment.getDermatologistAppointmentEndTime());
        dermatologistReportDTO.setDermatologistAppointmentStartTime(dermatologistAppointment.getDermatologistAppointmentStartTime());
        dermatologistReportDTO.setAppointmentId(dermatologistAppointment.getId());
        dermatologistReportDTO.setAppointmentPoints(dermatologistAppointment.getAppointmentPoints());
        dermatologistReportDTO.setStatusOfAppointment(dermatologistAppointment.getStatusOfAppointment());
        dermatologistReportDTO.setReportInfo(dermatologistReport.getReportInfo());
        dermatologistReportDTO.setAppointmentPrice(dermatologistAppointment.getAppointmentPrice());
        dermatologistReportDTO.setDurationOfAppointment(Duration.between(dermatologistAppointment.getDermatologistAppointmentStartTime(),dermatologistReportDTO.getDermatologistAppointmentEndTime()).toMinutes());

        return  dermatologistReportDTO;
    }
}

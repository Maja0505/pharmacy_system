package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.DermatologistReportDTO;
import com.isa.pharmacies_system.DTO.PharmacistReportDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.util.ArrayList;
import java.util.List;

public class PharmacistReportConverter {
    public PharmacistReportConverter() {
    }

    public List<PharmacistReportDTO> convertPharmacistReportToPharmacistReportDTOS(List<PharmacistReport> pharmacistReports){
        List<PharmacistReportDTO> pharmacistReportDTOS = new ArrayList<>();
        for (PharmacistReport pharmacistReport:pharmacistReports) {
            pharmacistReportDTOS.add(convertPharmacistReportToPharmacistReportDTO(pharmacistReport));
        }
        return pharmacistReportDTOS;
    }

    public PharmacistReportDTO convertPharmacistReportToPharmacistReportDTO(PharmacistReport pharmacistReport){
        PharmacistReportDTO pharmacistReportDTO = new PharmacistReportDTO();
        pharmacistReportDTO.setId(pharmacistReport.getId());

        Pharmacist pharmacist = pharmacistReport.getPharmacistAppointment().getPharmacistForAppointment();
        pharmacistReportDTO.setPharmacistId(pharmacist.getId());
        pharmacistReportDTO.setPharmacistFirstName(pharmacist.getFirstName());
        pharmacistReportDTO.setPharmacistLastName(pharmacist.getLastName());

        Pharmacy pharmacy = pharmacistReport.getPharmacistAppointment().getPharmacistForAppointment().getPharmacyForPharmacist();
        pharmacistReportDTO.setPharmacyId(pharmacy.getId());
        pharmacistReportDTO.setPharmacyName(pharmacy.getPharmacyName());

        PharmacistAppointment pharmacistAppointment = pharmacistReport.getPharmacistAppointment();
        pharmacistReportDTO.setDuration(pharmacistAppointment.getPharmacistAppointmentDuration());
        pharmacistReportDTO.setPharmacistAppointmentStartTime(pharmacistAppointment.getPharmacistAppointmentStartTime());
        pharmacistReportDTO.setAppointmentId(pharmacistAppointment.getId());
        pharmacistReportDTO.setAppointmentPoints(pharmacistAppointment.getAppointmentPoints());
        pharmacistReportDTO.setStatusOfAppointment(pharmacistAppointment.getStatusOfAppointment());
        pharmacistReportDTO.setReportInfo(pharmacistReport.getReportInfo());
        pharmacistReportDTO.setAppointmentPrice(pharmacistAppointment.getAppointmentPrice());

        return  pharmacistReportDTO;
    }
}

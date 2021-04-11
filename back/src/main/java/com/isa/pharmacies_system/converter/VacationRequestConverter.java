package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.VacationRequestDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.StatusOfVacationRequest;
import com.isa.pharmacies_system.domain.schedule.TypeOfVacationRequest;

public class VacationRequestConverter {

    public VacationRequestConverter() {
    }

    public PharmacistVacationRequest convertVacationRequestDTOToPharmacistRequest(VacationRequestDTO vacationRequestDTO){
        PharmacistVacationRequest pharmacistVacationRequest = new PharmacistVacationRequest();
        pharmacistVacationRequest.setStatusOfVacationRequest(StatusOfVacationRequest.Waiting);
        pharmacistVacationRequest.setTypeOfVacationRequest(TypeOfVacationRequest.Pharmacist_vacation_request);
        pharmacistVacationRequest.setVacationRequestNotes(vacationRequestDTO.getVacationRequestNotes());
        pharmacistVacationRequest.setTypeOfVacation(vacationRequestDTO.getTypeOfVacation());
        pharmacistVacationRequest.setVacationStartDate(vacationRequestDTO.getVacationStartDate());
        pharmacistVacationRequest.setVacationEndDate(vacationRequestDTO.getVacationEndDate());
        return pharmacistVacationRequest;
    }
}

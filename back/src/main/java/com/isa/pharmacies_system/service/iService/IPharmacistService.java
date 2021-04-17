package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistVacationRequest;
import com.isa.pharmacies_system.domain.user.Pharmacist;

import java.util.List;

public interface IPharmacistService {

    Pharmacist getPharmacist(Long id);
    void savePharmacist(Pharmacist pharmacist);
    Boolean changePassword(UserPasswordDTO pharmacistPasswordDTO);
    Boolean checkPassword(String firstPassword,String secondPassword);
    List<Pharmacist> getAllPharmacistsWithOpenAppointmentsByPharmacyId(Long pharmacyId, PharmacistAppointmentTimeDTO timeDTO);
    List<PharmacistVacationRequest> getAllFuturePharmacistVacationRequest(Long pharmacistId);
}

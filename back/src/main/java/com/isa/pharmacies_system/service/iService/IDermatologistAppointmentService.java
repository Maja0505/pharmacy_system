package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IDermatologistAppointmentService{

    DermatologistAppointment findOne(Long id);
    List<DermatologistAppointment> getOpenDermatologistAppointment(Long pharmacyId);
    void bookDermatologistAppointment(Patient patient, DermatologistAppointment dermatologistAppointment);
    Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id, int page);
    Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist,Long idPharmacy, int page);
    List<PatientAppointmentInfoDTO> sortByAppointmentEndTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc);
    Boolean cancelDermatologistAppointment(DermatologistAppointment dermatologistAppointment);


}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPharmacistAppointmentService{

    Page<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id,int page);
    List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    Boolean bookPharmacistAppointment(Long patientId, Long pharmacistId, PharmacistAppointmentTimeDTO timeDTO);

}

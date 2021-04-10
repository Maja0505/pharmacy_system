package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPharmacistAppointmentService {

    Page<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id,int page);
    List<PatientAppointmentInfoDTO> sortByPatientFirstName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByPatientLastName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByAppointmentStartTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByAppointmentPrice(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);


}

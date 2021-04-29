package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;

import java.util.List;

public interface IAppointmentService {

    List<PatientAppointmentInfoDTO> sortByPatientFirstName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc);
    List<PatientAppointmentInfoDTO> sortByPatientLastName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByPatientEmail(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByAppointmentStartTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);
    List<PatientAppointmentInfoDTO> sortByAppointmentPrice(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc);


}

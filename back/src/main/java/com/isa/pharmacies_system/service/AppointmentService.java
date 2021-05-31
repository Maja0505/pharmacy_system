package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.repository.IAppointmentRepository;
import com.isa.pharmacies_system.service.iService.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private IAppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByPatientFirstName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList,Boolean asc) {
        doSort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getPatientFirstName),asc);
        return patientAppointmentInfoDTOList;
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByPatientLastName(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        doSort(patientAppointmentInfoDTOList,Comparator.comparing(PatientAppointmentInfoDTO::getPatientLastName),asc);
        return patientAppointmentInfoDTOList;
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByPatientEmail(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        doSort(patientAppointmentInfoDTOList,Comparator.comparing(PatientAppointmentInfoDTO::getPatientEmail),asc);
        return patientAppointmentInfoDTOList;
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentStartTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        doSort(patientAppointmentInfoDTOList,Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentStartTime),asc);
        return patientAppointmentInfoDTOList;
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentPrice(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        doSort(patientAppointmentInfoDTOList,Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentPrice),asc);
        return patientAppointmentInfoDTOList;
    }

    private void doSort(List<PatientAppointmentInfoDTO> list,Comparator comparator,Boolean asc){
        if(asc){
            Collections.sort(list, comparator);
        }else{
            Collections.sort(list, comparator.reversed());
        }
    }

}

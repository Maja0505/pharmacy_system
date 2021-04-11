package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PharmacistAppointmentService implements IPharmacistAppointmentService {

    private IPharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Autowired
    public PharmacistAppointmentService(IPharmacistAppointmentRepository pharmacistAppointmentRepository) {
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
    }

    @Override
    public Page<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id,int page) {
        return pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id,PageRequest.of(page,10));
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentDuration(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        if(asc){
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration));
        }else{
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentDuration).reversed());
        }
        return patientAppointmentInfoDTOList;
    }


}

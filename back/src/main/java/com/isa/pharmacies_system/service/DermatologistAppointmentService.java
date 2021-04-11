package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.service.iService.IPatientService;

import java.util.Collections;
import java.util.Comparator;

import java.util.List;

@Service
public class DermatologistAppointmentService implements IDermatologistAppointmentService {

    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;
    private IPatientService patientService;

    @Autowired
    public DermatologistAppointmentService(IDermatologistAppointmentRepository dermatologistAppointmentRepository, IPatientService patientService) {

        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
        this.patientService = patientService;
    }

    @Override
    public DermatologistAppointment findOne(Long id){
        return dermatologistAppointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<DermatologistAppointment> getOpenDermatologistAppointment(){
        return dermatologistAppointmentRepository.getOpenDermatologistAppointment();
    }

    @Override
    public void bookDermatologistAppointment(Long patientId,DermatologistAppointment dermatologistAppointment){

        Patient patient = patientService.findOne(patientId);
        dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
        dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
        dermatologistAppointmentRepository.save(dermatologistAppointment);
    }

    @Override
    public Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologist(Long id, int page) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointment(id, PageRequest.of(page,10));
    }

    @Override
    public Page<DermatologistAppointment> getAllPastDermatologistAppointmentByDermatologistAndPharmacy(Long idDermatologist, Long idPharmacy, int page) {
        return dermatologistAppointmentRepository.findAllPastDermatologistAppointmentByPharmacy(idDermatologist,idPharmacy,PageRequest.of(page,10));
    }

    @Override
    public List<PatientAppointmentInfoDTO> sortByAppointmentEndTime(List<PatientAppointmentInfoDTO> patientAppointmentInfoDTOList, Boolean asc) {
        if(asc){
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentEndTime));
        }else{
            Collections.sort(patientAppointmentInfoDTOList, Comparator.comparing(PatientAppointmentInfoDTO::getAppointmentEndTime).reversed());
        }
        return patientAppointmentInfoDTOList;
    }

}

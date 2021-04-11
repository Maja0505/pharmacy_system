package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistAppointmentService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistAppointmentService implements IDermatologistAppointmentService {

    private IDermatologistAppointmentRepository dermatologistAppointmentRepository;
    private IPatientService patientService;

    public DermatologistAppointmentService(IDermatologistAppointmentRepository dermatologistAppointmentRepository, IPatientService patientService) {

        this.dermatologistAppointmentRepository = dermatologistAppointmentRepository;
        this.patientService = patientService;
    }

    public DermatologistAppointment findOne(Long id){
        return dermatologistAppointmentRepository.findById(id).orElse(null);
    }

    public List<DermatologistAppointment> getOpenDermatologistAppointment(){
        return dermatologistAppointmentRepository.getOpenDermatologistAppointment();
    }

    public void bookDermatologistAppointment(Long patientId,DermatologistAppointment dermatologistAppointment){

        Patient patient = patientService.findOne(patientId);
        dermatologistAppointment.setPatientWithDermatologistAppointment(patient);
        dermatologistAppointment.setStatusOfAppointment(StatusOfAppointment.Reserved);
        dermatologistAppointmentRepository.save(dermatologistAppointment);
    }




}

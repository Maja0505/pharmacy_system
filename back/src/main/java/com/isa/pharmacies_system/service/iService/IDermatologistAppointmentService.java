package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.DermatologistAppointmentDTO;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;

import java.util.List;

public interface IDermatologistAppointmentService {

    DermatologistAppointment findOne(Long id);
    List<DermatologistAppointment> getOpenDermatologistAppointment();
    void bookDermatologistAppointment(Long patientId,DermatologistAppointment dermatologistAppointment);
}

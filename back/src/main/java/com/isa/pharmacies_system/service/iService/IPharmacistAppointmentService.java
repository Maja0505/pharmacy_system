package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;

import java.util.List;

public interface IPharmacistAppointmentService {

    List<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id);

}

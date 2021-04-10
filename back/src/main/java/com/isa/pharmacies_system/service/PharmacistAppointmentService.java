package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.service.iService.IPharmacistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistAppointmentService implements IPharmacistAppointmentService {

    private IPharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Autowired
    public PharmacistAppointmentService(IPharmacistAppointmentRepository pharmacistAppointmentRepository) {
        this.pharmacistAppointmentRepository = pharmacistAppointmentRepository;
    }


    @Override
    public List<PharmacistAppointment> getAllPastPharmacistAppointmentByPharmacist(Long id) {
        List<PharmacistAppointment> list = pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id);
        for (PharmacistAppointment p: list
             ) {
            System.out.println(p.getPatientWithPharmacistAppointment().getFirstName());
        }
        return pharmacistAppointmentRepository.findAllPastPharmacistAppointment(id);
    }


}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.user.Patient;

import java.util.List;

public interface IPatientService {

    Patient findOne(Long id);
    List<Patient> findAll();
    void savePatient(Patient patient);
    Boolean changePassword(UserPasswordDTO userPasswordDTO);
}

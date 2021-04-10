package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PatientPasswordDTO;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.DTO.PatientInfoDTO;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findOne(Long id){
        return patientRepository.findById(id).orElseGet(null);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }

    public Boolean changePassword(PatientPasswordDTO patientPasswordDTO){
        Patient patient = findOne(patientPasswordDTO.getId());
        if(checkPassword(patient.getPassword(),patientPasswordDTO.getCurrentPassword()) && checkPassword(patientPasswordDTO.getNewPassword(),patientPasswordDTO.getConfirmedPassword())){
            patient.setPassword(patientPasswordDTO.getNewPassword());
            savePatient(patient);
            return true;
        }
        return false;
    }

    public Boolean checkPassword(String first, String second){
        return first.equals(second);
    }
}

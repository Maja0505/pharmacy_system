package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;
    private IMedicineService medicineService;

    public PatientService(IPatientRepository patientRepository, IMedicineService medicineService) {
        this.patientRepository = patientRepository;
        this.medicineService = medicineService;
    }

    public Patient findOne(Long id){
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }

    public Boolean changePassword(UserPasswordDTO userPasswordDTO){
        Patient patient = findOne(userPasswordDTO.getId());
        if(checkPassword(patient.getPassword(), userPasswordDTO.getConfirmedPassword()) && checkPassword(userPasswordDTO.getNewPassword(), userPasswordDTO.getConfirmedNewPassword())){
            patient.setPassword(userPasswordDTO.getNewPassword());
            savePatient(patient);
            return true;
        }
        return false;
    }

    public Boolean checkPassword(String first, String second){
        return first.equals(second);
    }

    public void addMedicineAllergie(Patient patient, Long medicineId){
        Medicine medicine = medicineService.findOne(medicineId);
        patient.getMedicineAllergies().add(medicine);
        savePatient(patient);
    }
}

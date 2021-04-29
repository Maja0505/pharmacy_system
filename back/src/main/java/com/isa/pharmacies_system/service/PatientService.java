package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findOne(Long id){
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    @Override
    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }

    //#1
    @Override
    public Boolean changePassword(UserPasswordDTO userPasswordDTO){
        Patient patient = findOne(userPasswordDTO.getId());
        if(checkPassword(patient.getPassword(), userPasswordDTO.getConfirmedPassword()) && checkPassword(userPasswordDTO.getNewPassword(), userPasswordDTO.getConfirmedNewPassword())){
            patient.setPassword(userPasswordDTO.getNewPassword());
            savePatient(patient);
            return true;
        }
        return false;
    }

    //#1
    @Override
    public void addMedicineAllergies(Patient patient, Medicine medicine){
        patient.getMedicineAllergies().add(medicine);
        savePatient(patient);
    }

    //#1
    @Override
    public Set<DermatologistAppointment> getDermatologistAppointmentForPatient(Long id){

        Patient patient = findOne(id);
        return patient.getDermatologistAppointment();
    }


    private Boolean checkPassword(String first, String second){
        return first.equals(second);
    }


    //Nemanja
    @Override
    public Boolean checkIsPatientAllergiesOnSomeMedicine(Long patientId,Medicine medicine){
        Patient patient = findOne(patientId);
        return  patient.getMedicineAllergies().stream().anyMatch(medicineAllergies -> medicineAllergies.equals(medicine));
    }


}

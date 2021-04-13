package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;
    private IMedicineService medicineService;

    public PatientService(IPatientRepository patientRepository, IMedicineService medicineService) {

        this.patientRepository = patientRepository;
        this.medicineService = medicineService;
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

    public Boolean checkPassword(String first, String second){
        return first.equals(second);
    }

    @Override
    public void addMedicineAllergies(Patient patient, Long medicineId){

        Medicine medicine = medicineService.findOne(medicineId);
        patient.getMedicineAllergies().add(medicine);
        savePatient(patient);
    }

    @Override
    public Set<DermatologistAppointment> getDermatologistAppointmentForPatient(Long id){

        Patient patient = findOne(id);
        return patient.getDermatologistAppointment();
    }


}

package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;
    private IMedicineRepository medicineRepository;

    public PatientService(IPatientRepository patientRepository, IMedicineRepository medicineRepository) {

        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
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
    public Boolean addMedicineAllergies(Long patientId, Long medicineId){
            Patient patient = patientRepository.findById(patientId).orElse(null);
            Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
            if(patient!=null && medicine!=null && !doesMedicineExistInAllergies(patient,medicine)){
                patient.getMedicineAllergies().add(medicine);
                savePatient(patient);
                return true;
            }
            return false;

    }

    //#1
    private Boolean doesMedicineExistInAllergies(Patient patient,Medicine medicine){
        return patient.getMedicineAllergies().stream().filter(m -> m.getId() == medicine.getId()).count()>0;
    }

    //#1
    @Override
    public Set<DermatologistAppointment> getDermatologistAppointmentForPatient(Long id){

        Patient patient = findOne(id);
        return patient.getDermatologistAppointment();
    }

    @Override
    public Set<DermatologistAppointment> getAllReservedDermatologistAppointmentsForPatient(Long id,int page){
        return patientRepository.getAllByDermatologistAppointment(id, PageRequest.of(page,10)).stream().filter(dermatologistAppointment -> dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved)).collect(Collectors.toSet());
    }

    @Override
    public Set<PharmacistAppointment> getAllReservedPharmacistAppointmentsForPatient(Long id, int page){
        return patientRepository.getAllByPharmacistAppointment(id, PageRequest.of(page,10)).stream().filter(dermatologistAppointment -> dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Reserved)).collect(Collectors.toSet());
    }

    @Override
    public Page<MedicineReservation> getAllMedicineReservationsForPatient(Long id, int page){
        return patientRepository.getAllByMedicineReservations(id, PageRequest.of(page,10));
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

package com.isa.pharmacies_system.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.MedicineDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.DTO.UserPersonalInfoDTO;
import com.isa.pharmacies_system.converter.MedicineConverter;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.converter.UserConverter;
import com.isa.pharmacies_system.domain.medicine.*;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.ConfirmationToken;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.service.iService.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private IPatientRepository patientRepository;
    private IMedicineRepository medicineRepository;
    private ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;
    private UserConverter userConverter;
    private MedicineConverter medicineConverter;
    private PharmacyConverter pharmacyConverter;

    @Autowired
    public PatientService(IPatientRepository patientRepository, IMedicineRepository medicineRepository, ConfirmationTokenService confirmationTokenService,EmailService emailService) {
    	this.confirmationTokenService = confirmationTokenService;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.emailService = emailService;
        this.userConverter = new UserConverter();
        this.medicineConverter = new MedicineConverter();
        this.pharmacyConverter = new PharmacyConverter();
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
        Patient patientNew = patientRepository.save(patient);
        
    }
    
    @Override
	public void createPatient(Patient patient) throws MailException, InterruptedException {
    	Patient patientNew = patientRepository.save(patient);
    	ConfirmationToken confirmationToken = confirmationTokenService.save((Users)patientNew);
		emailService.sendConfirmationMail(patientNew.getEmail(), confirmationToken.getConfirmationToken());
	}
    
    
	//#1
    @Override
    public Boolean changePassword(UserPasswordDTO userPasswordDTO){
        Patient patient = findOne(userPasswordDTO.getId());
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        if(b.matches(userPasswordDTO.getConfirmedPassword(),patient.getPassword()) && checkPassword(userPasswordDTO.getNewPassword(), userPasswordDTO.getConfirmedNewPassword())){
            patient.setPassword(b.encode(userPasswordDTO.getNewPassword()));
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

    @Override
    public Boolean removeMedicineAllergies(Long patientId, Long medicineId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if(patient!=null && medicine!=null && doesMedicineExistInAllergies(patient,medicine)){
            patient.getMedicineAllergies().remove(medicine);
            savePatient(patient);
            return true;
        }
        return false;
    }


    @Override
    public void resetAllPenaltiesForPatient() {
        List<Patient> patients = patientRepository.findAllPatientsWithMoreThenZeroPenalties();
        try{
            for (Patient patient: patients) {
                patient.setPenalty(0);
                patientRepository.save(patient);
            }
        }catch (Exception e){}

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

    @Override
    public List<EPrescription> getAllEPrescriptionsForPatient(Long id) {
        return patientRepository.getAllEPrescriptionsForPatient(id);
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

	
    //#1
    @Override
    public List<Pharmacy> getSubscriptionPharmaciesForPatient(Long id){
        return List.copyOf(findOne(id).getPharmaciesSubscription());
    }


    //#1
    @Override
    public List<UserPersonalInfoDTO> getAllDermatologistForPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        List<UserPersonalInfoDTO> dermatologists = new ArrayList<>();
        if(patient != null){
            dermatologists = addDermatologistIfPatientHasDermatologistAppointmentWithIt(patient,dermatologists);
        }
        return dermatologists;
    }

    private List<UserPersonalInfoDTO> addDermatologistIfPatientHasDermatologistAppointmentWithIt(Patient patient,List<UserPersonalInfoDTO> dermatologists){
        Set<DermatologistAppointment> dermatologistAppointments = patient.getDermatologistAppointment();
        for (DermatologistAppointment dermatologistAppointment:dermatologistAppointments) {
            if(dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired) && dermatologists.stream().filter(dermatologist -> dermatologist.getId() == dermatologistAppointment.getDermatologistForAppointment().getId()).count() == 0){
                dermatologists.add(userConverter.convertDermatologistPersonalInfoToDTO(dermatologistAppointment.getDermatologistForAppointment()));
            }
        }
        return dermatologists;
    }

    //#1
    @Override
    public List<UserPersonalInfoDTO> getAllPharmacistForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        List<UserPersonalInfoDTO> pharmacists = new ArrayList<>();
        if(patient != null){
            pharmacists = addPharmacistIfPatientHasPharmacistAppointmentWithIt(patient,pharmacists);
        }
        return pharmacists;
    }


    private List<UserPersonalInfoDTO> addPharmacistIfPatientHasPharmacistAppointmentWithIt(Patient patient,List<UserPersonalInfoDTO> pharmacists){
        Set<PharmacistAppointment> pharmacistAppointments = patient.getPharmacistAppointments();
        for (PharmacistAppointment pharmacistAppointment:pharmacistAppointments) {
            if(pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired) && pharmacists.stream().filter(dermatologist -> dermatologist.getId() == pharmacistAppointment.getPharmacistForAppointment().getId()).count() == 0){
                pharmacists.add(userConverter.convertPharmacistPersonalInfoToDTO(pharmacistAppointment.getPharmacistForAppointment()));
            }
        }
        return pharmacists;
    }


    //#1
    @Override
    public List<MedicineDTO> getAllMedicinesForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        List<MedicineDTO> medicines = new ArrayList<>();
        if(patient != null){
            medicines = addMedicineIfPatientHasMedicineReservationWithIt(patient,medicines);
            medicines = addMedicineIfPatientHasEPrescriptionWithIt(patient,medicines);
        }
        return medicines;
    }

    private List<MedicineDTO> addMedicineIfPatientHasMedicineReservationWithIt(Patient patient, List<MedicineDTO> medicines){
        Set<MedicineReservation> medicineReservations = patient.getPatientMedicineReservations();
        for (MedicineReservation medicineReservation:medicineReservations) {
            if(medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.FINISHED) && medicines.stream().filter(medicine -> medicine.getMedicineId() == medicineReservation.getReservedMedicine().getId()).count() == 0){
                medicines.add(medicineConverter.convertMedicineToMedicineDTO(medicineReservation.getReservedMedicine()));
            }
        }
        return  medicines;
    }

    private List<MedicineDTO> addMedicineIfPatientHasEPrescriptionWithIt(Patient patient, List<MedicineDTO> medicines){
        Set<EPrescription> ePrescriptions = patient.getPatientEPrescriptions();
        for (EPrescription ePrescription:ePrescriptions) {
            Set<EPrescriptionItem> ePrescriptionItems = ePrescription.getePrescriptionItems();
            for (EPrescriptionItem ePrescriptionItem: ePrescriptionItems) {
                if(medicines.stream().filter(medicine -> medicine.getMedicineId() == ePrescriptionItem.getMedicineItem().getId()).count() == 0){
                    medicines.add(medicineConverter.convertMedicineToMedicineDTO(ePrescriptionItem.getMedicineItem()));
                }
            }
        }
        return  medicines;
    }

    //#1
    @Override
    public List<PharmacyDTO> getAllPharmaciesForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        List<PharmacyDTO> pharmacies = new ArrayList<>();
        if(patient != null){
           pharmacies = addPharmacyIfPatientHasMedicineReservationInIt(patient,pharmacies);
           pharmacies = addPharmacyIfPatientHasPharmacistAppointmentInIt(patient,pharmacies);
           pharmacies = addPharmacyIfPatientHasDermatologistAppointmentInIt(patient,pharmacies);

        }
        return pharmacies;
    }

    private List<PharmacyDTO> addPharmacyIfPatientHasMedicineReservationInIt(Patient patient,List<PharmacyDTO> pharmacies){
        Set<MedicineReservation> medicineReservations = patient.getPatientMedicineReservations();
        for (MedicineReservation medicineReservation:medicineReservations) {
            if(medicineReservation.getStatusOfMedicineReservation().equals(StatusOfMedicineReservation.FINISHED) && pharmacies.stream().filter(pharmacy -> pharmacy.getId() == medicineReservation.getPharmacyForMedicineReservation().getId()).count() == 0){
                pharmacies.add(pharmacyConverter.convertPharmacyInfoToPharmacyDTO(medicineReservation.getPharmacyForMedicineReservation()));
            }
        }
        return pharmacies;
    }

    private List<PharmacyDTO> addPharmacyIfPatientHasPharmacistAppointmentInIt(Patient patient, List<PharmacyDTO> pharmacies){
        Set<PharmacistAppointment> pharmacistAppointments = patient.getPharmacistAppointments();
        for (PharmacistAppointment pharmacistAppointment:pharmacistAppointments) {
            if(pharmacistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired) && pharmacies.stream().filter(pharmacy -> pharmacy.getId() == pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist().getId()).count() == 0){
                pharmacies.add(pharmacyConverter.convertPharmacyInfoToPharmacyDTO(pharmacistAppointment.getPharmacistForAppointment().getPharmacyForPharmacist()));
            }
        }
        return pharmacies;
    }

    private List<PharmacyDTO> addPharmacyIfPatientHasDermatologistAppointmentInIt(Patient patient, List<PharmacyDTO> pharmacies){
        Set<DermatologistAppointment> dermatologistAppointments = patient.getDermatologistAppointment();
        for (DermatologistAppointment dermatologistAppointment:dermatologistAppointments) {
            if(dermatologistAppointment.getStatusOfAppointment().equals(StatusOfAppointment.Expired) && pharmacies.stream().filter(pharmacy -> pharmacy.getId() == dermatologistAppointment.getPharmacyForDermatologistAppointment().getId()).count() == 0){
                pharmacies.add(pharmacyConverter.convertPharmacyInfoToPharmacyDTO(dermatologistAppointment.getPharmacyForDermatologistAppointment()));
            }
        }
        return pharmacies;
    }


}

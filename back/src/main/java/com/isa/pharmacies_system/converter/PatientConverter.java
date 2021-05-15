package com.isa.pharmacies_system.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.isa.pharmacies_system.DTO.MedicineForAllergiesDTO;
import com.isa.pharmacies_system.DTO.PatientAdditionalInfoDTO;
import com.isa.pharmacies_system.DTO.PatientAppointmentInfoDTO;
import com.isa.pharmacies_system.DTO.PatientNewDTO;
import com.isa.pharmacies_system.domain.complaint.Complaint;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.rating.Rating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.CategoryOfPatient;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.TypeOfUser;

public class PatientConverter{

    private  DermatologistAppointmentConverter dermatologistAppointmentConverter;
    
    private PasswordEncoder passwordEncoder;
    
    public PatientConverter() {
		// TODO Auto-generated constructor stub
	}
    
    public PatientConverter(PasswordEncoder passwordEncoder) {
        dermatologistAppointmentConverter = new DermatologistAppointmentConverter();
        this.passwordEncoder = passwordEncoder;
    }

    public PatientAdditionalInfoDTO convertPatientAdditionalInfoToDTO(Patient patient){

        PatientAdditionalInfoDTO patientAdditionalInfoDTO = new PatientAdditionalInfoDTO();
        patientAdditionalInfoDTO.setFirstName(patient.getFirstName());
        patientAdditionalInfoDTO.setLastName(patient.getLastName());
        patientAdditionalInfoDTO.setAddress(patient.getUserAddress());
        patientAdditionalInfoDTO.setId(patient.getId());
        patientAdditionalInfoDTO.setPhoneNumber(patient.getPhoneNumber());
        patientAdditionalInfoDTO.setEmail(patient.getEmail());
        patientAdditionalInfoDTO.setPatientPoints(patient.getPatientPoints());
        patientAdditionalInfoDTO.setCategoryOfPatient(patient.getCategoryOfPatient());

        List<MedicineForAllergiesDTO> medicineForAllergiesDTOList = new ArrayList<>();
        for (Medicine medicine: patient.getMedicineAllergies()) {
            MedicineForAllergiesDTO medicineForAllergiesDTO = new MedicineForAllergiesDTO();
            medicineForAllergiesDTO.setMedicineId(medicine.getId());
            medicineForAllergiesDTO.setMedicineName(medicine.getMedicineName());
            medicineForAllergiesDTOList.add(medicineForAllergiesDTO);
        }
        patientAdditionalInfoDTO.setMedicineForAllergiesDTO(medicineForAllergiesDTOList);
        return patientAdditionalInfoDTO;
    }

    public List<PatientAppointmentInfoDTO> convertPatientPharmacistAppointmentInfoToDTO(Page<PharmacistAppointment> pharmacistAppointments){
        List<PatientAppointmentInfoDTO> list = new ArrayList<>();
        for (PharmacistAppointment appointment :pharmacistAppointments
             ) {
            list.add(makePharmacistAppointmentInfoDTO(appointment));
        }
        return list;
    }

    public List<PatientAppointmentInfoDTO> convertPatientDermatologistAppointmentInfoToDTO(Page<DermatologistAppointment> dermatologistAppointments){
        List<PatientAppointmentInfoDTO> list = new ArrayList<>();
        for (DermatologistAppointment appointment :dermatologistAppointments
        ) {
            list.add(makeDermatologistAppointmentInfoDTO(appointment));
        }
        return list;
    }

    private PatientAppointmentInfoDTO makeDermatologistAppointmentInfoDTO(DermatologistAppointment appointment){
        PatientAppointmentInfoDTO patientAppointmentInfoDTO = new PatientAppointmentInfoDTO();
        Patient patient = appointment.getPatientWithDermatologistAppointment();
        makePatientInfoDTO(patient,patientAppointmentInfoDTO);
        patientAppointmentInfoDTO.setAppointmentStartTime(appointment.getDermatologistAppointmentStartTime());
        patientAppointmentInfoDTO.setAppointmentEndTime(appointment.getDermatologistAppointmentEndTime());
        patientAppointmentInfoDTO.setAppointmentPrice(appointment.getAppointmentPrice());
        return patientAppointmentInfoDTO;
    }

    private PatientAppointmentInfoDTO makePharmacistAppointmentInfoDTO(PharmacistAppointment appointment){
        PatientAppointmentInfoDTO patientAppointmentInfoDTO = new PatientAppointmentInfoDTO();
        Patient patient = appointment.getPatientWithPharmacistAppointment();
        makePatientInfoDTO(patient,patientAppointmentInfoDTO);
        patientAppointmentInfoDTO.setAppointmentDuration(appointment.getPharmacistAppointmentDuration());
        patientAppointmentInfoDTO.setAppointmentPrice(appointment.getAppointmentPrice());
        patientAppointmentInfoDTO.setAppointmentStartTime(appointment.getPharmacistAppointmentStartTime());
        return patientAppointmentInfoDTO;
    }

    private void makePatientInfoDTO(Patient patient,PatientAppointmentInfoDTO patientAppointmentInfoDTO){
        patientAppointmentInfoDTO.setPatientId(patient.getId());
        patientAppointmentInfoDTO.setPatientEmail(patient.getEmail());
        patientAppointmentInfoDTO.setPatientFirstName(patient.getFirstName());
        patientAppointmentInfoDTO.setPatientLastName(patient.getLastName());
    }
    
    public Patient convertPatientNewDTOToPatient(PatientNewDTO patientNewDTO) throws Exception {
    	Patient patient = new Patient();
    	System.out.println("Da li je patientNew null" + patientNewDTO.getPassword());
    	patient.setCategoryOfPatient(CategoryOfPatient.Regular);
    	patient.setDermatologistAppointment(new HashSet<DermatologistAppointment>());
    	patient.setEmail(patientNewDTO.getEmail());
    	patient.setEnabled(false);
    	patient.setFirstLogin(true);
    	patient.setFirstName(patientNewDTO.getFirstName());
    	patient.setLastName(patientNewDTO.getLastName());
    	if (!patientNewDTO.getPassword().equals(patientNewDTO.getConfirmPassword())){
    		throw new Exception("Password and confirm password aren't equal!");
    	}
    	patient.setPassword(passwordEncoder.encode(patientNewDTO.getPassword()));
    	patient.setMedicineAllergies(new HashSet<Medicine>());
    	patient.setPatientComplaints(new HashSet<Complaint>());
    	patient.setPatientEPrescriptions(new HashSet<EPrescription>());
    	patient.setPatientMedicineReservations(new HashSet<MedicineReservation>());
    	patient.setPatientPoints(0);
    	patient.setPatientRatings(new HashSet<Rating>());
    	patient.setPatientRecipe(new HashSet<Recipe>());
    	patient.setPharmaciesSubscription(new HashSet<Pharmacy>());
    	patient.setPharmacistAppointments(new HashSet<PharmacistAppointment>());
    	patient.setPhoneNumber(patientNewDTO.getPhoneNumber());
    	patient.setTypeOfUser(TypeOfUser.Patient);
    	patient.setUserAddress(patientNewDTO.getResidentialAddress());
    	return patient;
    }

}

package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface IPatientService {

    Patient findOne(Long id);
    List<Patient> findAll();
    void savePatient(Patient patient);
    Boolean changePassword(UserPasswordDTO userPasswordDTO);
    Boolean addMedicineAllergies(Long patientId, Long medicineId);
    Set<DermatologistAppointment> getDermatologistAppointmentForPatient(Long id);
    Boolean checkIsPatientAllergiesOnSomeMedicine(Long patientId,Medicine medicine);
    Set<DermatologistAppointment> getAllReservedDermatologistAppointmentsForPatient(Long id,int page);
    Set<PharmacistAppointment> getAllReservedPharmacistAppointmentsForPatient(Long id, int page);
    Page<MedicineReservation> getAllMedicineReservationsForPatient(Long id, int page);
    List<EPrescription> getAllEPrescriptionsForPatient(Long id);
    List<Pharmacy> getSubscriptionPharmaciesForPatient(Long id);
}

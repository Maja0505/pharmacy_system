package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.DermatologistComplaintDTO;
import com.isa.pharmacies_system.DTO.PharmacistComplaintDTO;
import com.isa.pharmacies_system.DTO.PharmacyComplaintDTO;
import com.isa.pharmacies_system.domain.complaint.Complaint;

public interface IComplaintService {
	List<Complaint> getAllComplaintWithoutAnswer();
	boolean checkIfPatientHasAppointmentAtPharmacist(long pharmacistId, long patientId);
	boolean checkIfPatientHasAppointmentAtDermatologist(long dermatologistId, long patientId);
	boolean checkIfPatientHasDermatologistAppointmentInPharmacy(long pharmacyId, long patientId);
	boolean checkIfPatientHasPharmacistAppointmentInPharmacy(long pharmacyId, long patientId);
	boolean checkIfPatientGetEPrescriptionInPharmacy(long pharmacyId, long patientId);
	boolean checkIfPatientHasFinishedMedicineReservationInPharmacy(long pharmacyId, long patientId);
	void createPharmacistComplaint(PharmacistComplaintDTO pharmacistComplaintDTO);
	void createDermatologistComplaint(DermatologistComplaintDTO dermatologistComplaintDTO);
	void createPharmacyComplaint(PharmacyComplaintDTO pharmacyComplaintDTO);
}

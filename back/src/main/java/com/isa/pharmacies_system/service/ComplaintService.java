package com.isa.pharmacies_system.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.DermatologistComplaintDTO;
import com.isa.pharmacies_system.DTO.PharmacistComplaintDTO;
import com.isa.pharmacies_system.DTO.PharmacyComplaintDTO;
import com.isa.pharmacies_system.domain.complaint.Complaint;
import com.isa.pharmacies_system.domain.complaint.DermatologistComplaint;
import com.isa.pharmacies_system.domain.complaint.PharmacistComplaint;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;
import com.isa.pharmacies_system.domain.complaint.StatusOfComplaint;
import com.isa.pharmacies_system.domain.complaint.TypeOfComplaint;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfEPrescription;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.repository.IComplaintRepository;
import com.isa.pharmacies_system.repository.IDermatologistAppointmentRepository;
import com.isa.pharmacies_system.repository.IDermatologistComplaintRepository;
import com.isa.pharmacies_system.repository.IDermatologistRepository;
import com.isa.pharmacies_system.repository.IEPrescriptionRepository;
import com.isa.pharmacies_system.repository.IMedicineReservationRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacistAppointmentRepository;
import com.isa.pharmacies_system.repository.IPharmacistComplaintRepository;
import com.isa.pharmacies_system.repository.IPharmacistRepository;
import com.isa.pharmacies_system.repository.IPharmacyComplaintRepository;
import com.isa.pharmacies_system.repository.IPharmacyRepository;
import com.isa.pharmacies_system.service.iService.IComplaintService;

@Service
public class ComplaintService implements IComplaintService {
	
	private IComplaintRepository iComplaintRepository;
	private IPharmacistAppointmentRepository iPharmacistAppointmentRepository;
	private IDermatologistAppointmentRepository iDermatologistAppointmentRepository;
	private IEPrescriptionRepository iEPrescriptionRepository;
	private IMedicineReservationRepository iMedicineReservationRepository;
	private IPatientRepository iPatientRepository;
	private IPharmacistRepository iPharmacistRepository;
	private IDermatologistRepository iDermatologistRepository;
	private IPharmacyRepository iPharmacyRepository;
	private IPharmacistComplaintRepository iPharmacistComplaintRepository;
	private IPharmacyComplaintRepository iPharmacyComplaintRepository;
	private IDermatologistComplaintRepository iDermatologistComplaintRepository;
	
	@Autowired
	public ComplaintService(IComplaintRepository iComplaintRepository, IPharmacistAppointmentRepository iPharmacistAppointmentRepository, IDermatologistAppointmentRepository iDermatologistAppointmentRepository, IEPrescriptionRepository iEPrescriptionRepository, IMedicineReservationRepository iMedicineReservationRepository, IPatientRepository iPatientRepository, IPharmacistRepository iPharmacistRepository, IDermatologistRepository iDermatologistRepository, IPharmacyRepository iPharmacyRepository, IPharmacistComplaintRepository iPharmacistComplaintRepository, IPharmacyComplaintRepository iPharmacyComplaintRepository, IDermatologistComplaintRepository iDermatologistComplaintRepository) {
		// TODO Auto-generated constructor stub
		this.iComplaintRepository=iComplaintRepository;
		
		this.iPharmacistAppointmentRepository = iPharmacistAppointmentRepository;
		this.iDermatologistAppointmentRepository = iDermatologistAppointmentRepository;
		this.iEPrescriptionRepository=iEPrescriptionRepository;
		this.iMedicineReservationRepository = iMedicineReservationRepository;
		this.iPatientRepository= iPatientRepository;
		this.iPharmacistRepository = iPharmacistRepository;
		this.iDermatologistRepository = iDermatologistRepository;
		this.iPharmacyRepository = iPharmacyRepository;
		this.iPharmacistComplaintRepository=iPharmacistComplaintRepository;
		this.iPharmacyComplaintRepository = iPharmacyComplaintRepository;
		this.iDermatologistComplaintRepository = iDermatologistComplaintRepository;
	}
	
	@Override
	public List<Complaint> getAllComplaintWithoutAnswer() {
		return iComplaintRepository.findAll().stream().filter(complaint->complaint.getStatusOfComplaint() == StatusOfComplaint.Waiting_for_response).collect(Collectors.toList());
	}

	@Override
	public boolean checkIfPatientHasAppointmentAtPharmacist(long pharmacistId, long patientId) {
		PharmacistAppointment pharmacistAppointment = iPharmacistAppointmentRepository.findAll().stream().filter(pharmacistAppointmentIt -> pharmacistAppointmentIt.getPharmacistForAppointment().getId()== pharmacistId && pharmacistAppointmentIt.getPatientWithPharmacistAppointment().getId()==patientId).findAny().orElse(null);
		return pharmacistAppointment==null?false:true;
	}

	@Override
	public boolean checkIfPatientHasAppointmentAtDermatologist(long dermatologistId, long patientId) {
		DermatologistAppointment dermatologistAppointment = iDermatologistAppointmentRepository.findAll().stream().filter(dermatologistAppointmentIt -> dermatologistAppointmentIt.getDermatologistForAppointment().getId()== dermatologistId && dermatologistAppointmentIt.getPatientWithDermatologistAppointment().getId()==patientId).findAny().orElse(null);
		return dermatologistAppointment==null?false:true;
	}

	@Override
	public boolean checkIfPatientHasDermatologistAppointmentInPharmacy(long pharmacyId, long patientId) {
		DermatologistAppointment dermatologistAppointment = iDermatologistAppointmentRepository.findAll().stream().filter(dermatologistAppointmentIt -> dermatologistAppointmentIt.getPharmacyForDermatologistAppointment().getId()== pharmacyId && dermatologistAppointmentIt.getPatientWithDermatologistAppointment().getId()==patientId).findAny().orElse(null);
		return dermatologistAppointment==null?false:true;
	}

	@Override
	public boolean checkIfPatientHasPharmacistAppointmentInPharmacy(long pharmacyId, long patientId) {
		PharmacistAppointment pharmacistAppointment = iPharmacistAppointmentRepository.findAll().stream().filter(pharmacistAppointmentIt -> pharmacistAppointmentIt.getPharmacistForAppointment().getPharmacyForPharmacist().getId()== pharmacyId && pharmacistAppointmentIt.getPatientWithPharmacistAppointment().getId()==patientId).findAny().orElse(null);
		return pharmacistAppointment==null?false:true;
	}

	@Override
	public boolean checkIfPatientGetEPrescriptionInPharmacy(long pharmacyId, long patientId) {
		EPrescription ePrescription = iEPrescriptionRepository.findAll().stream().filter(ePrescriptionIt -> ePrescriptionIt.getPatientForEPrescription().getId() == patientId && ePrescriptionIt.getPharmacyForEPrescription().getId() == pharmacyId && ePrescriptionIt.getStatusOfEPrescription() == StatusOfEPrescription.PROCESSED).findAny().orElse(null);
		return ePrescription==null?false:true;
	}

	@Override
	public boolean checkIfPatientHasFinishedMedicineReservationInPharmacy(long pharmacyId, long patientId) {
		MedicineReservation medicineReservation = iMedicineReservationRepository.findAll().stream().filter(medicineReservationIt -> medicineReservationIt.getPharmacyForMedicineReservation().getId() == pharmacyId && medicineReservationIt.getPatientForMedicineReservation().getId() == patientId && medicineReservationIt.getStatusOfMedicineReservation() == StatusOfMedicineReservation.FINISHED).findAny().orElse(null);
		return medicineReservation==null?false:true;
	}
	
	private PharmacistComplaint convertPharmacistComplaintDTOToPharmacistComplaint(PharmacistComplaintDTO pharmacistComplaintDTO) {
		PharmacistComplaint pharmacistComplaint = new PharmacistComplaint();
		pharmacistComplaint.setContent(pharmacistComplaintDTO.getContent());
		pharmacistComplaint.setPatientWithComplaint(iPatientRepository.findById(pharmacistComplaintDTO.getPatientId()).orElse(null));
		pharmacistComplaint.setStatusOfComplaint(StatusOfComplaint.Waiting_for_response);
		pharmacistComplaint.setSerialNumber(UUID.randomUUID().toString());
		pharmacistComplaint.setPharmacistForComplaint(iPharmacistRepository.findById(pharmacistComplaintDTO.getPharmacistId()).orElse(null));
		pharmacistComplaint.setTypeOfComplaint(TypeOfComplaint.Pharmacist_complaint);
		return pharmacistComplaint;
	}
	
	private DermatologistComplaint convertDermatologistComplaintDTOToDermatologistComplaint(DermatologistComplaintDTO dermatologistComplaintDTO) {
		DermatologistComplaint dermatologistComplaint = new DermatologistComplaint();
		dermatologistComplaint.setContent(dermatologistComplaintDTO.getContent());
		dermatologistComplaint.setPatientWithComplaint(iPatientRepository.findById(dermatologistComplaintDTO.getPatientId()).orElse(null));
		dermatologistComplaint.setStatusOfComplaint(StatusOfComplaint.Waiting_for_response);
		dermatologistComplaint.setSerialNumber(UUID.randomUUID().toString());
		dermatologistComplaint.setDermatologistForComplaint(iDermatologistRepository.findById(dermatologistComplaintDTO.getDermatologistId()).orElse(null));
		dermatologistComplaint.setTypeOfComplaint(TypeOfComplaint.Dermatologist_complaint);
		return dermatologistComplaint;
	}
	
	private PharmacyComplaint convertPharmacyComplaintDTOToPharmacyComplaint(PharmacyComplaintDTO pharmacyComplaintDTO) {
		PharmacyComplaint pharmacyComplaint = new PharmacyComplaint();
		pharmacyComplaint.setContent(pharmacyComplaintDTO.getContent());
		pharmacyComplaint.setPatientWithComplaint(iPatientRepository.findById(pharmacyComplaintDTO.getPatientId()).orElse(null));
		pharmacyComplaint.setStatusOfComplaint(StatusOfComplaint.Waiting_for_response);
		pharmacyComplaint.setSerialNumber(UUID.randomUUID().toString());
		pharmacyComplaint.setPharmacyForComplaint(iPharmacyRepository.findById(pharmacyComplaintDTO.getPharmacyId()).orElse(null));
		pharmacyComplaint.setTypeOfComplaint(TypeOfComplaint.Pharmacy_complaint);
		return pharmacyComplaint;
	}

	@Override
	public void createPharmacistComplaint(PharmacistComplaintDTO pharmacistComplaintDTO) {
		// TODO Auto-generated method stub
		PharmacistComplaint pharmacistComplaint = iPharmacistComplaintRepository.save(convertPharmacistComplaintDTOToPharmacistComplaint(pharmacistComplaintDTO));
		iComplaintRepository.save((Complaint)pharmacistComplaint);
	}

	@Override
	public void createDermatologistComplaint(DermatologistComplaintDTO dermatologistComplaintDTO) {
		// TODO Auto-generated method stub
		DermatologistComplaint dermatologistComplaint = iDermatologistComplaintRepository.save(convertDermatologistComplaintDTOToDermatologistComplaint(dermatologistComplaintDTO));
		iComplaintRepository.save((Complaint)dermatologistComplaint);
	}

	@Override
	public void createPharmacyComplaint(PharmacyComplaintDTO pharmacyComplaintDTO) {
		PharmacyComplaint pharmacyComplaint = iPharmacyComplaintRepository.save(convertPharmacyComplaintDTOToPharmacyComplaint(pharmacyComplaintDTO));
		iComplaintRepository.save((Complaint)pharmacyComplaint);
	}
	
	
}

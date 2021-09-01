package com.isa.pharmacies_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.DermatologistComplaintResponseDTO;
import com.isa.pharmacies_system.DTO.PharmacistComplaintResponseDTO;
import com.isa.pharmacies_system.DTO.PharmacyComplaintResponseDTO;
import com.isa.pharmacies_system.domain.complaint.ComplaintResponse;
import com.isa.pharmacies_system.domain.complaint.DermatologistComplaintResponse;
import com.isa.pharmacies_system.domain.complaint.PharmacistComplaintResponse;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaintResponse;
import com.isa.pharmacies_system.repository.IComplaintResponseRepository;
import com.isa.pharmacies_system.repository.IDermatologistComplaintRepository;
import com.isa.pharmacies_system.repository.IDermatologistComplaintResponseRepository;
import com.isa.pharmacies_system.repository.IPharmacistComplaintRepository;
import com.isa.pharmacies_system.repository.IPharmacistComplaintResponseRepository;
import com.isa.pharmacies_system.repository.IPharmacyComplaintRepository;
import com.isa.pharmacies_system.repository.IPharmacyComplaintResponseRepository;
import com.isa.pharmacies_system.repository.ISystemAdminRepository;
import com.isa.pharmacies_system.service.iService.IComplaintResponseService;

@Service
public class ComplaintResponseService implements IComplaintResponseService {
	
	private IPharmacistComplaintResponseRepository iPharmacistComplaintResponseRepository;
	private IPharmacyComplaintResponseRepository iPharmacyComplaintResponseRepository;
	private IDermatologistComplaintResponseRepository iDermatologistComplaintResponseRepository;
	private IPharmacistComplaintRepository iPharmacistComplaintRepository;
	private IPharmacyComplaintRepository iPharmacyComplaintRepository;
	private IDermatologistComplaintRepository iDermatologistComplaintRepository;
	private ISystemAdminRepository iSystemAdminRepository;
	private IComplaintResponseRepository iComplaintResponseRepository;
	private EmailService emailService;
	
	
	@Autowired
	public ComplaintResponseService(IPharmacistComplaintResponseRepository iPharmacistComplaintResponseRepository,
			IPharmacyComplaintResponseRepository iPharmacyComplaintResponseRepository,
			IDermatologistComplaintResponseRepository iDermatologistComplaintResponseRepository,
			IPharmacistComplaintRepository iPharmacistComplaintRepository,
			IPharmacyComplaintRepository iPharmacyComplaintRepository,
			IDermatologistComplaintRepository iDermatologistComplaintRepository,
			ISystemAdminRepository iSystemAdminRepository, IComplaintResponseRepository iComplaintResponseRepository, EmailService emailService) {
		this.iPharmacistComplaintResponseRepository = iPharmacistComplaintResponseRepository;
		this.iPharmacyComplaintResponseRepository = iPharmacyComplaintResponseRepository;
		this.iComplaintResponseRepository = iComplaintResponseRepository;
		this.iDermatologistComplaintResponseRepository = iDermatologistComplaintResponseRepository;
		this.iPharmacistComplaintRepository = iPharmacistComplaintRepository;
		this.iPharmacyComplaintRepository = iPharmacyComplaintRepository;
		this.iDermatologistComplaintRepository = iDermatologistComplaintRepository;
		this.iSystemAdminRepository = iSystemAdminRepository;
		this.emailService = emailService;
	}
	
	private PharmacistComplaintResponse convertPharmacistComplaintResponseDTOToPharmacistComplaintResponse(PharmacistComplaintResponseDTO pharmacistComplaintResponseDTO) {
		PharmacistComplaintResponse pharmacistComplaintResponse = new PharmacistComplaintResponse();
		pharmacistComplaintResponse.setPharmacistComplaint(iPharmacistComplaintRepository.findById(pharmacistComplaintResponseDTO.getPharmacistComplaintId()).orElse(null));
		pharmacistComplaintResponse.setSystemAdminForComplaintResponse(iSystemAdminRepository.findById(pharmacistComplaintResponseDTO.getSystemAdminId()).orElse(null));
		pharmacistComplaintResponse.setComplaintResponseContent(pharmacistComplaintResponseDTO.getComplaintResponseContent());
		return pharmacistComplaintResponse;
	}
	
	private DermatologistComplaintResponse convertDermatologistComplaintResponseDTOToDermatologistComplaintResponse(DermatologistComplaintResponseDTO dermatologistComplaintResponseDTO) {
		DermatologistComplaintResponse dermatologistComplaintResponse = new DermatologistComplaintResponse();
		dermatologistComplaintResponse.setDermatologistComplaint(iDermatologistComplaintRepository.findById(dermatologistComplaintResponseDTO.getDermatologistComplaintId()).orElse(null));
		dermatologistComplaintResponse.setSystemAdminForComplaintResponse(iSystemAdminRepository.findById(dermatologistComplaintResponseDTO.getSystemAdminId()).orElse(null));
		dermatologistComplaintResponse.setComplaintResponseContent(dermatologistComplaintResponseDTO.getComplaintResponseContent());
		return dermatologistComplaintResponse;
	}
	
	private PharmacyComplaintResponse convertPharmacyComplaintResponseDTOToPharmacyComplaintResponse(PharmacyComplaintResponseDTO pharmacyComplaintResponseDTO) {
		PharmacyComplaintResponse pharmacyComplaintResponse = new PharmacyComplaintResponse();
		pharmacyComplaintResponse.setPharmacyComplaint(iPharmacyComplaintRepository.findById(pharmacyComplaintResponseDTO.getPharmacyComplaintId()).orElse(null));
		pharmacyComplaintResponse.setSystemAdminForComplaintResponse(iSystemAdminRepository.findById(pharmacyComplaintResponseDTO.getSystemAdminId()).orElse(null));
		pharmacyComplaintResponse.setComplaintResponseContent(pharmacyComplaintResponseDTO.getComplaintResponseContent());
		return pharmacyComplaintResponse;
	}

	@Override
	public void createPharmacistComplaintResponse(PharmacistComplaintResponseDTO pharmacistComplaintResponseDTO) {
		PharmacistComplaintResponse pharmacistComplaintResponse = iPharmacistComplaintResponseRepository.save(convertPharmacistComplaintResponseDTOToPharmacistComplaintResponse(pharmacistComplaintResponseDTO));
		iComplaintResponseRepository.save((ComplaintResponse) pharmacistComplaintResponse);
		try {
			emailService.sendResponseForPharmacistComplaint(pharmacistComplaintResponse);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createDermatologistComplaintResponse(
			DermatologistComplaintResponseDTO dermatologistComplaintResponseDTO) {
		DermatologistComplaintResponse dermatologistComplaintResponse = iDermatologistComplaintResponseRepository.save(convertDermatologistComplaintResponseDTOToDermatologistComplaintResponse(dermatologistComplaintResponseDTO));
		iComplaintResponseRepository.save((ComplaintResponse) dermatologistComplaintResponse);
		try {
			emailService.sendResponseForDermatologistComplaint(dermatologistComplaintResponse);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createPharmacyComplaintResponse(PharmacyComplaintResponseDTO pharmacyComplaintResponseDTO) {
		PharmacyComplaintResponse pharmacyComplaintResponse = iPharmacyComplaintResponseRepository.save(convertPharmacyComplaintResponseDTOToPharmacyComplaintResponse(pharmacyComplaintResponseDTO));
		iComplaintResponseRepository.save((ComplaintResponse) pharmacyComplaintResponse);
		try {
			emailService.sendResponseForPharmacyComplaint(pharmacyComplaintResponse);
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}

package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.DermatologistNewDTO;
import com.isa.pharmacies_system.DTO.UserPasswordDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfVacationRequest;
import com.isa.pharmacies_system.converter.DermatologistConverter;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IDermatologistRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.IDermatologistService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class DermatologistService implements IDermatologistService {

    private IDermatologistRepository dermatologistRepository;
    private DermatologistConverter dermatologistConverter;
    private IUserRepository iUserRepository;
    private IPatientRepository patientRepository;

    @Autowired
    public DermatologistService(IDermatologistRepository dermatologistRepository,IUserRepository iUserRepository) {
        this.dermatologistRepository = dermatologistRepository;
        this.iUserRepository= iUserRepository;
        this.dermatologistConverter = new DermatologistConverter();
    }

    @Override
    public Dermatologist getDermatologist(Long id){
    	return dermatologistRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDermatologist(Dermatologist dermatologist) {
        dermatologistRepository.save(dermatologist);
    }

    @Override
    public Boolean changePassword(UserPasswordDTO dermatologistPasswordDTO) {
        Dermatologist dermatologist = getDermatologist(dermatologistPasswordDTO.getId());

        if(checkPassword(dermatologistPasswordDTO.getConfirmedPassword(),dermatologist.getPassword()) &&
                checkPassword(dermatologistPasswordDTO.getNewPassword(),dermatologistPasswordDTO.getConfirmedNewPassword())){

            dermatologist.setPassword(dermatologistPasswordDTO.getNewPassword());
            saveDermatologist(dermatologist);
            return true;

        }
        return false;
    }

    @Override
    public Boolean checkPassword(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }

    @Override
    public List<DermatologistVacationRequest> getAllFutureDermatologistVacation(Long dermatologistId) {
        Dermatologist dermatologist = getDermatologist(dermatologistId);
        return dermatologist.getDermatologistVacationRequests().stream()
                .filter(dvq -> (dvq.getVacationEndDate().isAfter(LocalDate.now()) && !dvq.getStatusOfVacationRequest().equals(StatusOfVacationRequest.Declined)))
                .collect(Collectors.toList());
    }

    @Override
	public Dermatologist create(DermatologistNewDTO dermatologistNewDTO) {
		Dermatologist dermatologist = dermatologistConverter.convertDermatologistNewDTOToDermatologist(dermatologistNewDTO);
		iUserRepository.save((Users) dermatologist);
		return dermatologistRepository.save(dermatologist);
	}

	@Override
	public Dermatologist getById(Long id) throws Exception {
		Dermatologist dermatologist = dermatologistRepository.findById(id).orElse(null);
		if (dermatologist == null) {
			throw new Exception("Don't exist dermatologist with id "+id);
		}
		return dermatologist;
	}

	@Override
	public List<Dermatologist> getAll() {
		return dermatologistRepository.findAll();
	}
}

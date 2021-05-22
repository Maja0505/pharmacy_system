package com.isa.pharmacies_system.converter;

import java.util.HashSet;

import com.isa.pharmacies_system.DTO.DermatologistNewDTO;
import com.isa.pharmacies_system.domain.complaint.DermatologistComplaint;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.rating.DermatologistRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.DermatologistVacationRequest;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.TypeOfUser;

public class DermatologistConverter {
	public DermatologistConverter() {}
	
	public Dermatologist convertDermatologistNewDTOToDermatologist(DermatologistNewDTO dermatologistNewDTO) {
		Dermatologist dermatologist = new Dermatologist();
		dermatologist.setEmail(dermatologistNewDTO.getEmail());
		dermatologist.setFirstName(dermatologistNewDTO.getFirstName());
		dermatologist.setLastName(dermatologistNewDTO.getLastName());
		dermatologist.setFirstLogin(true);
		dermatologist.setEnabled(true);
		dermatologist.setPassword(dermatologistNewDTO.getPassword());
		dermatologist.setPhoneNumber(dermatologistNewDTO.getPhoneNumber());
		dermatologist.setTypeOfUser(TypeOfUser.Dermatologist);
		dermatologist.setDermatologistAppointments(new HashSet<DermatologistAppointment>());
		dermatologist.setDermatologistAverageRating(0.0);
		dermatologist.setDermatologistComplaints(new HashSet<DermatologistComplaint>());
		dermatologist.setDermatologistRatings(new HashSet<DermatologistRating>());
		dermatologist.setDermatologistSchedules(new HashSet<WorkerSchedule>());
		dermatologist.setDermatologistVacationRequests(new HashSet<DermatologistVacationRequest>());
		dermatologist.setUserAddress(dermatologistNewDTO.getResidentialAddress());
		return dermatologist;
	}
	
	public DermatologistNewDTO convertDermatologistToDermatologistNewDTO(Dermatologist dermatologist) {
		DermatologistNewDTO dermatologistNewDTO = new DermatologistNewDTO();
		dermatologistNewDTO.setResidentialAddress(dermatologist.getUserAddress());
		dermatologistNewDTO.setEmail(dermatologist.getEmail());
		dermatologistNewDTO.setFirstName(dermatologist.getFirstName());
		dermatologistNewDTO.setLastName(dermatologist.getLastName());
		dermatologistNewDTO.setPassword(dermatologist.getPassword());
		dermatologistNewDTO.setPhoneNumber(dermatologist.getPhoneNumber());
		return dermatologistNewDTO;
	}
}

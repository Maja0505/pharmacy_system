package com.isa.pharmacies_system.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.Promotions;
import com.isa.pharmacies_system.domain.rating.PharmacyRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;

public class PharmacyConverter {

	public PharmacyConverter() {
	}

	public PharmacyDTO convertPharmacyToPharmacyDTO(Pharmacy pharmacy){

		PharmacyDTO pharmacyDTO = new PharmacyDTO();
		pharmacyDTO.setId(pharmacy.getId());
		pharmacyDTO.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyDTO.setPharmacyAddress(pharmacy.getPharmacyAddress());
		pharmacyDTO.setPharmacyAverageRating(pharmacy.getPharmacyAverageRating());
		return pharmacyDTO;
	}

	public List<PharmacyDTO> convertPharmacyListToPharmacyDTOList(List<Pharmacy> pharmacies){
		List<PharmacyDTO> pharmacyDTOS = new ArrayList<>();
		for (Pharmacy pharmacy: pharmacies) {
			pharmacyDTOS.add(convertPharmacyToPharmacyDTO(pharmacy));
		}
		return pharmacyDTOS;
	}
	
	public Pharmacy convertPharmacyNewDTOToPharmacy(PharmacyNewDTO pharmacyNewDTO) {
		Pharmacy pharmacy= new Pharmacy();
		pharmacy.setDermatologistAppointmentsInPharmacy(new HashSet<DermatologistAppointment>());
		pharmacy.setDermatologistsInPharmacy(new HashSet<Dermatologist>());
		pharmacy.setPharmacistsInPharmacy(new HashSet<Pharmacist>());
		pharmacy.setPharmacyAddress(pharmacyNewDTO.getPharmacyAddress());
		pharmacy.setPharmacyAverageRating(0.0);
		pharmacy.setPharmacyComplaints(new HashSet<PharmacyComplaint>());
		pharmacy.setPharmacyDescription(pharmacyNewDTO.getPharmacyDescription());
		pharmacy.setPharmacyMedicineReservations(new HashSet<MedicineReservation>());
		pharmacy.setPharmacyName(pharmacyNewDTO.getPharmacyName());
		pharmacy.setPharmacyRatings(new HashSet<PharmacyRating>());
		pharmacy.setPromotionsForPharmacy(new HashSet<Promotions>());
		pharmacy.setWorkerSchedule(new HashSet<WorkerSchedule>());
		return pharmacy;
	}
	
	public PharmacyNewDTO convertPharmacyToPharmacyNewDTO(Pharmacy pharmacy) {
		PharmacyNewDTO pharmacyNewDTO= new PharmacyNewDTO();
		pharmacyNewDTO.setPharmacyAddress(pharmacy.getPharmacyAddress());
		pharmacyNewDTO.setPharmacyDescription(pharmacy.getPharmacyDescription());
		pharmacyNewDTO.setPharmacyName(pharmacy.getPharmacyName());
		return pharmacyNewDTO;
	}
}

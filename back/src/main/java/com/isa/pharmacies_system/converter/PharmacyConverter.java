package com.isa.pharmacies_system.converter;

import java.util.HashSet;

import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.Promotions;
import com.isa.pharmacies_system.domain.rating.PharmacyRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.dto.PharmacyNewDTO;

public class PharmacyConverter {
	public PharmacyConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public Pharmacy convertPharmacyNewDTOToPharmacy(PharmacyNewDTO pharmacyNewDTO) {
		Pharmacy pharmacy= new Pharmacy();
		pharmacy.setDermatologistAppointmentsInPharmacy(new HashSet<DermatologistAppointment>());
		pharmacy.setDermatologistsInPharmacy(new HashSet<Dermatologist>());
		pharmacy.setPharmacistsInPharmacy(new HashSet<Pharmacist>());
		pharmacy.setPharmacyAddress(new Address(0,pharmacyNewDTO.getStreetName(),pharmacyNewDTO.getStreetNumber(),pharmacyNewDTO.getCity(), pharmacyNewDTO.getCountry(),0.0,0.0));
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
		pharmacyNewDTO.setCity(pharmacy.getPharmacyAddress().getCity());
		pharmacyNewDTO.setCountry(pharmacy.getPharmacyAddress().getCountry());
		pharmacyNewDTO.setPharmacyDescription(pharmacy.getPharmacyDescription());
		pharmacyNewDTO.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyNewDTO.setStreetName(pharmacy.getPharmacyAddress().getStreetName());
		pharmacyNewDTO.setStreetNumber(pharmacy.getPharmacyAddress().getStreetNumber());
		return pharmacyNewDTO;
	}
}

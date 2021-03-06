package com.isa.pharmacies_system.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaint;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import com.isa.pharmacies_system.domain.pharmacy.Promotions;
import com.isa.pharmacies_system.domain.rating.PharmacyRating;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.WorkerSchedule;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.service.PriceListService;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.data.domain.Page;

public class PharmacyConverter {

	private IPriceListService priceListService;

	public PharmacyConverter(IPriceListService priceListService) {
		this.priceListService = priceListService;
	}
	public PharmacyConverter() {
	}

	public PharmacyDTO convertPharmacyToPharmacyDTO(Pharmacy pharmacy){

		PharmacyDTO pharmacyDTO = new PharmacyDTO();
		pharmacyDTO.setId(pharmacy.getId());
		pharmacyDTO.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyDTO.setPharmacyAddress(pharmacy.getPharmacyAddress());
		pharmacyDTO.setCityForPharmacy(pharmacy.getPharmacyAddress().getCity());
		pharmacyDTO.setPharmacyAverageRating(pharmacy.getPharmacyAverageRating());
		PriceListForAppointmentDTO priceList = priceListService.findPriceListByPharmacyId(pharmacyDTO.getId());
		pharmacyDTO.setPriceListForAppointmentDTO(priceList);
		return pharmacyDTO;
	}


	public PharmacyProfileDTO convertPharmacyToPharmacyProfileDTO(Pharmacy pharmacy){

		PharmacyProfileDTO pharmacyDTO = new PharmacyProfileDTO();
		pharmacyDTO.setId(pharmacy.getId());
		pharmacyDTO.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyDTO.setStreetName(pharmacy.getPharmacyAddress().getStreetName());
		pharmacyDTO.setCityForPharmacy(pharmacy.getPharmacyAddress().getCity());
		pharmacyDTO.setStreetNumber(pharmacy.getPharmacyAddress().getStreetNumber());
		pharmacyDTO.setCountry(pharmacy.getPharmacyAddress().getCountry());
		PriceListForAppointmentDTO priceList = priceListService.findPriceListByPharmacyId(pharmacyDTO.getId());
		pharmacyDTO.setPharmacistPerHour(priceList.getPharmacistAppointmentPricePerHour());
		pharmacyDTO.setDermatologistPerHour(priceList.getDermatologistAppointmentPricePerHour());
		pharmacyDTO.setPharmacyAverageRating(pharmacy.getPharmacyAverageRating());
		pharmacyDTO.setPharmacyDescription(pharmacy.getPharmacyDescription());
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

	public PharmacyWithMedicinePriceDTO convertPharmacyStorageItemToPharmacyWithMedicinePriceDTO(PharmacyStorageItem pharmacyStorageItem){
		PharmacyWithMedicinePriceDTO pharmacyWithMedicinePriceDTO = new PharmacyWithMedicinePriceDTO();
		pharmacyWithMedicinePriceDTO.setId(pharmacyStorageItem.getPharmacyStorageWithItem().getPharmacy().getId());
		pharmacyWithMedicinePriceDTO.setPharmacyName(pharmacyStorageItem.getPharmacyStorageWithItem().getPharmacy().getPharmacyName());
		pharmacyWithMedicinePriceDTO.setPharmacyAddress(pharmacyStorageItem.getPharmacyStorageWithItem().getPharmacy().getPharmacyAddress());
		pharmacyWithMedicinePriceDTO.setPharmacyAverageRating(pharmacyStorageItem.getPharmacyStorageWithItem().getPharmacy().getPharmacyAverageRating());
		return pharmacyWithMedicinePriceDTO;
	}

	public List<PharmacyWithMedicinePriceDTO> convertListPharmacyStorageItemsToPharmacyWithMedicinePriceDTOS(List<PharmacyStorageItem> pharmacyStorageItems){
		List<PharmacyWithMedicinePriceDTO> pharmacyWithMedicinePriceDTOList  = new ArrayList<>();
		for (PharmacyStorageItem pharmacyStorageItem: pharmacyStorageItems) {
			PharmacyWithMedicinePriceDTO pharmacyWithMedicinePriceDTO = convertPharmacyStorageItemToPharmacyWithMedicinePriceDTO(pharmacyStorageItem);
			pharmacyWithMedicinePriceDTOList.add(pharmacyWithMedicinePriceDTO);
		}
		return pharmacyWithMedicinePriceDTOList;
	}

	//potrebe su samo osnovne info(bez cene)
	public PharmacyDTO convertPharmacyInfoToPharmacyDTO(Pharmacy pharmacy){

		PharmacyDTO pharmacyDTO = new PharmacyDTO();
		pharmacyDTO.setId(pharmacy.getId());
		pharmacyDTO.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyDTO.setPharmacyAddress(pharmacy.getPharmacyAddress());
		pharmacyDTO.setPharmacyAverageRating(pharmacy.getPharmacyAverageRating());
		return pharmacyDTO;
	}

	//Nemanja
	public List<PharmacyWhereDermatologistWorkDTO> convertPharmacyListToPharmacyWhereDermatologistWorkDTOList(List<Pharmacy> pharmacies){
		List<PharmacyWhereDermatologistWorkDTO> list = new ArrayList<>();
		for(Pharmacy p : pharmacies){
			PharmacyWhereDermatologistWorkDTO pDTO = new PharmacyWhereDermatologistWorkDTO();
			pDTO.setPharmacyId(p.getId());
			pDTO.setPharmacyName(p.getPharmacyName());
			list.add(pDTO);
		}
		return list;
	}

}

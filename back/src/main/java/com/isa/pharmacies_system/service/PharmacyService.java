package com.isa.pharmacies_system.service;

import java.util.*;
import java.util.stream.Collectors;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import com.isa.pharmacies_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import com.isa.pharmacies_system.domain.storage.PharmacyStorage;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.storage.Storage;
import com.isa.pharmacies_system.domain.storage.TypeOfStorage;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Service
public class PharmacyService implements IPharmacyService {
	
	private IPharmacyRepository iPharmacyRepository;
	private IPharmacyStorageRepository iPharmacyStorageRepository;
	private IStorageRepository iStorageRepository;
	private IPriceListRepository iPriceListRepository;
	private PharmacyConverter pharmacyConverter;
	private UtilityMethods utilityMethods;
	private IWorkingHoursRepository workingHoursRepository;
	
	@Autowired
	public PharmacyService(IPharmacyRepository iPharmacyRepository, IPriceListRepository iPriceListRepository, IPharmacyStorageRepository iPharmacyStorageRepository, IStorageRepository iStorageRepository, IWorkingHoursRepository workingHoursRepository) {
		this.iPharmacyRepository=iPharmacyRepository;
		this.iPriceListRepository= iPriceListRepository;
		this.iPharmacyStorageRepository=iPharmacyStorageRepository;
		this.iStorageRepository= iStorageRepository;
		this.workingHoursRepository = workingHoursRepository;
		this.pharmacyConverter = new PharmacyConverter();
		this.utilityMethods = new UtilityMethods();
	}
	
	@Override
	public Pharmacy create(PharmacyNewDTO pharmacyNewDTO) throws Exception {
		Pharmacy pharmacy = iPharmacyRepository.save(pharmacyConverter.convertPharmacyNewDTOToPharmacy(pharmacyNewDTO));
		
		addPriceListForCreatedPharmacy(pharmacy, pharmacyNewDTO.getPriceForDermatologistAppointment(),pharmacyNewDTO.getPriceForPharmacistAppointment());
		addStorageForCreatedPharmacy(pharmacy);
		return pharmacy;
	}
	
	private void addPriceListForCreatedPharmacy(Pharmacy pharmacy, double priceForDermatologistAppointment,double priceForPharmacistAppointment) throws Exception {
		System.out.println();
		PriceList priceList = new PriceList();
		priceList.setPharmacy(pharmacy);
		priceList.setDermatologistAppointmentPricePerHour(priceForDermatologistAppointment);
		priceList.setPharmacistAppointmentPricePerHour(priceForPharmacistAppointment);
		priceList.setMedicinePrices(new HashSet<MedicinePrice>());
		iPriceListRepository.save(priceList);
	}
	
	private void addStorageForCreatedPharmacy(Pharmacy pharmacy) throws Exception {
		PharmacyStorage pharmacyStorage = new PharmacyStorage();
		pharmacyStorage.setTypeOfStorage(TypeOfStorage.Pharmacy_storage);
		pharmacyStorage.setPharmacy(pharmacy);
		pharmacyStorage.setPharmacyStorageItems(new HashSet<PharmacyStorageItem>());
		iPharmacyStorageRepository.save(pharmacyStorage);
		iStorageRepository.save((Storage) pharmacyStorage);
		
	}


	@Override
	public List<Pharmacy> getAll() {
		return iPharmacyRepository.findAll();
	}



	@Override
	public List<PharmacyDTO> searchPharmacyByNameAndCity(String word, List<PharmacyDTO> pharmacyDTOS) {
		return pharmacyDTOS.stream().filter(pharmacyDTO -> pharmacyDTO.getPharmacyName().toLowerCase().contains(word.toLowerCase()) || pharmacyDTO.getPharmacyAddress().getCity().toLowerCase().contains(word.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public List<PharmacyDTO> filterPharmacy(FilteringPharmacyDTO filteringPharmacyDTO) {
		List<PharmacyDTO> filterList;
		filterList = filteringPharmacyDTO.getPharmacyDTOS().stream().filter(pharmacyDTO ->
				checkFilterForRating(pharmacyDTO,filteringPharmacyDTO)
						|| checkFilterForPharmacistAppointmentPrice(pharmacyDTO,filteringPharmacyDTO)
						|| checkFilterForDermatologistAppointmentPrice(pharmacyDTO,filteringPharmacyDTO)).collect(Collectors.toList());
		return filterList;
	}

	private Boolean checkFilterForRating(PharmacyDTO pharmacyDTO,FilteringPharmacyDTO filteringPharmacyDTO){
		return (pharmacyDTO.getPharmacyAverageRating() >= 0 && pharmacyDTO.getPharmacyAverageRating() <= 2 && filteringPharmacyDTO.getFilterListForRating().get(0))
				|| (pharmacyDTO.getPharmacyAverageRating() >= 2  && pharmacyDTO.getPharmacyAverageRating() <= 3 && filteringPharmacyDTO.getFilterListForRating().get(1))
				|| (pharmacyDTO.getPharmacyAverageRating() >= 3  && pharmacyDTO.getPharmacyAverageRating() <= 4 && filteringPharmacyDTO.getFilterListForRating().get(2))
				|| (pharmacyDTO.getPharmacyAverageRating() >= 4  && pharmacyDTO.getPharmacyAverageRating() <= 5 && filteringPharmacyDTO.getFilterListForRating().get(3));
	}

	private Boolean checkFilterForPharmacistAppointmentPrice(PharmacyDTO pharmacyDTO,FilteringPharmacyDTO filteringPharmacyDTO){
		 return (pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() <10 && filteringPharmacyDTO.getFilterListForPharmacistAppointmentPricePerHour().get(0))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() >= 10 && pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() <= 20 && filteringPharmacyDTO.getFilterListForPharmacistAppointmentPricePerHour().get(1))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() >= 20  && pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() <= 30 && filteringPharmacyDTO.getFilterListForPharmacistAppointmentPricePerHour().get(2))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() >= 30 && pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() <= 40 && filteringPharmacyDTO.getFilterListForPharmacistAppointmentPricePerHour().get(3))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getPharmacistAppointmentPricePerHour() > 40 && filteringPharmacyDTO.getFilterListForPharmacistAppointmentPricePerHour().get(4));
	}

	private Boolean checkFilterForDermatologistAppointmentPrice(PharmacyDTO pharmacyDTO,FilteringPharmacyDTO filteringPharmacyDTO){
		return (pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() >= 10 && pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() <= 20 && filteringPharmacyDTO.getFilterListForDermatologistAppointmentPricePerHour().get(1))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() >= 20  && pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() <= 30 && filteringPharmacyDTO.getFilterListForDermatologistAppointmentPricePerHour().get(2))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() >= 30 && pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() <= 40 && filteringPharmacyDTO.getFilterListForDermatologistAppointmentPricePerHour().get(3))
				|| (pharmacyDTO.getPriceListForAppointmentDTO().getDermatologistAppointmentPricePerHour() > 40 && filteringPharmacyDTO.getFilterListForDermatologistAppointmentPricePerHour().get(4));
	}


	@Override
	public Pharmacy getById(Long id) throws Exception {
		Pharmacy pharmacy = iPharmacyRepository.findById(id).orElse(null);
		if (pharmacy==null) {
			throw new Exception("Don't exist pharmacy with id "+id+"!");
		}
		return pharmacy;
	}

	//#1[3.16]
	//dobavi sve apoteke
	//proveri za svaku apoteku da li ima farmaceuta koji ima zadato radno vreme u tom peridu
	//proveri za svakog farmaceuta da li ima sloboda izabrani termin
	@Override
	public List<Pharmacy> getAllPharmacyWithFreePharmacistByDate(PharmacistAppointmentTimeDTO timeDTO){
		List<Pharmacy> pharmacies = iPharmacyRepository.findAll();
		List<Pharmacy> pharmacyList = new ArrayList<>();
		for (Pharmacy pharmacy:pharmacies) {
			List<Pharmacist> pharmacists = List.copyOf(pharmacy.getPharmacistsInPharmacy());
			pharmacists = pharmacists.stream().filter(pharmacist -> doesPharmacistWorkInSelectedDate(timeDTO,pharmacist) && doesPharmacistHaveOpenSelectedAppointment(timeDTO,pharmacist)).collect(Collectors.toList());
			if(pharmacists.stream().count() > 0){
				pharmacyList.add(pharmacy);
			}
		}
		return pharmacyList;
	}

	//#1 da li farmaceut ima radno vreme izabranog datuma
	private Boolean doesPharmacistWorkInSelectedDate(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
		return (workingHoursRepository.getWorkingHourByDate(pharmacist.getId(),timeDTO.getStartTime(),timeDTO.getStartTime().plusMinutes((long)timeDTO.getDuration()))).stream().count()>0;
	}

	//#1[3.16]Korak1/2/3
	//da li je selektovan datum slobodan kod odredjenog farmaceuta
	private Boolean doesPharmacistHaveOpenSelectedAppointment(PharmacistAppointmentTimeDTO timeDTO, Pharmacist pharmacist){
		return pharmacist.getPharmacistAppointments().stream().filter(pharmacistAppointment -> utilityMethods.isSelectedDateReserved(timeDTO,pharmacistAppointment)).count() == 0;
	}

	@Override
	public List<PharmacyDTO> sortByPharmacyName(List<PharmacyDTO> pharmacies, Boolean asc) {
		if(asc){
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getPharmacyName));
		}else{
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getPharmacyName).reversed());
		}
		return pharmacies;
	}

	@Override
	public List<PharmacyDTO> sortByPharmacyRating(List<PharmacyDTO> pharmacies, Boolean asc) {
		if(asc){
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getPharmacyAverageRating));
		}else{
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getPharmacyAverageRating).reversed());
		}
		return pharmacies;
	}

	@Override
	public List<PharmacyDTO> sortByPharmacyCity(List<PharmacyDTO> pharmacies, Boolean asc) {
		if(asc){
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getCityForPharmacy));
		}else{
			Collections.sort(pharmacies, Comparator.comparing(PharmacyDTO::getCityForPharmacy).reversed());
		}
		return pharmacies;
	}

	//Nemanja
	@Override
	public List<Pharmacy> getAllPharmacyByDermatologistId(Long dermatologistId) {
		List<Pharmacy> allPharmacy = iPharmacyRepository.findAll();
		return allPharmacy.stream()
				.filter(p -> p.getDermatologistsInPharmacy().stream()
				.filter(d -> d.getId() == dermatologistId).count() > 0).collect(Collectors.toList());

	}

}

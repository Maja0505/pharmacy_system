package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.domain.schedule.PharmacistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfWorkingHours;
import com.isa.pharmacies_system.domain.schedule.WorkingHours;
import com.isa.pharmacies_system.domain.user.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.PharmacyNewDTO;
import com.isa.pharmacies_system.converter.PharmacyConverter;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import com.isa.pharmacies_system.domain.storage.PharmacyStorage;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.storage.Storage;
import com.isa.pharmacies_system.domain.storage.TypeOfStorage;
import com.isa.pharmacies_system.repository.IPharmacyRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageRepository;
import com.isa.pharmacies_system.repository.IPriceListRepository;
import com.isa.pharmacies_system.repository.IStorageRepository;
import com.isa.pharmacies_system.service.iService.IPharmacyService;

@Service
public class PharmacyService implements IPharmacyService {
	
	private IPharmacyRepository iPharmacyRepository;
	private IPharmacyStorageRepository iPharmacyStorageRepository;
	private IStorageRepository iStorageRepository;
	private IPriceListRepository iPriceListRepository;
	private PharmacyConverter pharmacyConverter;
	private UtilityMethods utilityMethods;
	
	@Autowired
	public PharmacyService(IPharmacyRepository iPharmacyRepository, IPriceListRepository iPriceListRepository,IPharmacyStorageRepository iPharmacyStorageRepository,IStorageRepository iStorageRepository) {
		this.iPharmacyRepository=iPharmacyRepository;
		this.iPriceListRepository= iPriceListRepository;
		this.iPharmacyStorageRepository=iPharmacyStorageRepository;
		this.iStorageRepository= iStorageRepository;
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
			pharmacists = pharmacists.stream().filter(pharmacist -> utilityMethods.isPharmacistWorkInSelectedDate(timeDTO,pharmacist)).collect(Collectors.toList());
			pharmacists = pharmacists.stream().filter(pharmacist -> utilityMethods.doesPharmacistHaveOpenSelectedAppoinemnt(timeDTO, pharmacist)).collect(Collectors.toList());
			if(pharmacists.stream().count() > 0){
				pharmacyList.add(pharmacy);
			}
		}
		return pharmacyList;
	}

}

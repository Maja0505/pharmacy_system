package com.isa.pharmacies_system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.DTO.MedicineReviewDTO;
import com.isa.pharmacies_system.DTO.PharmacyForEPrescriptionDTO;
import com.isa.pharmacies_system.DTO.PharmacyForMedicineDTO;
import com.isa.pharmacies_system.converter.MedicineConverter;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineInfo;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.repository.IMedicineInfoRepository;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.service.iService.IMedicineService;
import com.isa.pharmacies_system.service.iService.IPharmacyService;
import com.isa.pharmacies_system.service.iService.IPharmacyStorageService;
import com.isa.pharmacies_system.service.iService.IPriceListService;

@Service
public class MedicineService implements IMedicineService {

    private IMedicineRepository medicineRepository;
    private IMedicineInfoRepository iMedicineInfoRepository;
    private MedicineConverter medicineConverter;
    private IPharmacyService iPharmacyService;
    private IPharmacyStorageService iPharmacyStorageService;
    private IPriceListService iPriceListService;
    
    @Autowired
    public MedicineService(IMedicineRepository medicineRepository, IMedicineInfoRepository iMedicineInfoRepository,IPharmacyService iPharmacyService,IPharmacyStorageService iPharmacyStorageService, IPriceListService iPriceListService) {
        this.medicineRepository = medicineRepository;
        this.iMedicineInfoRepository = iMedicineInfoRepository;
        this.medicineConverter = new MedicineConverter();
        this.iPharmacyService = iPharmacyService;
        this.iPharmacyStorageService = iPharmacyStorageService;
        this.iPriceListService = iPriceListService;
    }
    
    @Override
    public Medicine findOne(Long id){
      return medicineRepository.findById(id).orElse(null);
    }

	@Override
	public List<Medicine> getAll() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine create(MedicineNewDTO medicineNewDTO) throws Exception {
		Medicine medicine = medicineConverter.convertMedicineNewDTOToMedicine(medicineNewDTO);
		medicine.setAlternativeMedicines(getSetAlternativeMedicines(medicineNewDTO));
		iMedicineInfoRepository.save((MedicineInfo) medicine);
		return medicineRepository.save(medicine);
	}
	
	@Override
	public List<MedicineReviewDTO> getMedicinesAndPharmacyWithMedicines(){
		List<MedicineReviewDTO> medicinesAndPharmacyWithMedicines = new ArrayList<MedicineReviewDTO>();
		for (Medicine medicineIt : getAll()) {
			MedicineReviewDTO medicine = new MedicineReviewDTO(medicineIt.getMedicineName(),medicineIt.getTypeOfMedicine(),medicineIt.getMedicineAverageRating());
			medicine.setPharmacyWithMedicine(getPharmaciesWithMedicine(medicineIt));
			medicinesAndPharmacyWithMedicines.add(medicine);
		}
		return medicinesAndPharmacyWithMedicines;
	}
	
	@Override
	public List<PharmacyForEPrescriptionDTO> getPharmaciesWithTotalPriceForEPrescription(List<EPrescriptionItem> listOfEPrescriptionItems){
		List<PharmacyForEPrescriptionDTO> pharmaciesAndTotalPrice = new ArrayList<PharmacyForEPrescriptionDTO>();
		List<Pharmacy> pharmacies = getListOfPharmacyWithAllMedicineForEPrescription(listOfEPrescriptionItems);
		for (Pharmacy pharmacy : pharmacies) {
			double totalPrice = 0;
			for (EPrescriptionItem ePrescriptionItem : listOfEPrescriptionItems) {
				MedicinePrice medicinePrice = iPriceListService.getPriceForMedicineInPharmacy(ePrescriptionItem.getMedicineItem(), pharmacy);
				totalPrice+=ePrescriptionItem.getMedicineAmount() * medicinePrice.getMedicinePrice();
			}
			pharmaciesAndTotalPrice.add(new PharmacyForEPrescriptionDTO(pharmacy.getId(),pharmacy.getPharmacyName(),pharmacy.getPharmacyAddress(),pharmacy.getPharmacyAverageRating(),totalPrice));
		}
		return pharmaciesAndTotalPrice;
	}

	private List<Pharmacy> getListOfPharmacyWithAllMedicineForEPrescription(List<EPrescriptionItem> listOfEPrescriptionItems) {
		List<Pharmacy> pharmacies = iPharmacyService.getAll();
		for (EPrescriptionItem ePrescriptionItem : listOfEPrescriptionItems) {
			for (Pharmacy pharmacy : iPharmacyService.getAll()) {
				if (!iPharmacyStorageService.checkMedicineHaveInPharmacy(pharmacy, ePrescriptionItem.getMedicineItem())){
					pharmacies = pharmacies.stream().filter(pharmaciesIt -> pharmaciesIt.getId()!=pharmacy.getId()).collect(Collectors.toList());
				}
			}
		}
		return pharmacies;
	}

	private List<PharmacyForMedicineDTO> getPharmaciesWithMedicine(Medicine medicineIt) {
		List<PharmacyForMedicineDTO> pharmaciesForMedicine = new ArrayList<PharmacyForMedicineDTO>();
		for (Pharmacy pharmacyIt : iPharmacyService.getAll()) {
			MedicinePrice medicinePrice = iPriceListService.getPriceForMedicineInPharmacy(medicineIt, pharmacyIt);
			if (iPharmacyStorageService.checkMedicineHaveInPharmacy(pharmacyIt, medicineIt) && medicinePrice!=null) {
				pharmaciesForMedicine.add(new PharmacyForMedicineDTO(pharmacyIt.getPharmacyName(), medicinePrice.getMedicinePrice()));
			}
		}
		return pharmaciesForMedicine;
	}
	
	private Set<MedicineInfo> getSetAlternativeMedicines(MedicineNewDTO medicineNewDTO) throws Exception {
		Set<MedicineInfo> medicines = new HashSet<MedicineInfo>();
		for (String medicneNameIt : medicineNewDTO.getNamesOfAlternativeMedicines()) {
			Medicine alternativeMedicine = getMedicineByName(medicneNameIt);
			if (alternativeMedicine!=null) {
				medicines.add(alternativeMedicine);
			} else {
				throw new Exception("Don't exist medicine with name "+medicneNameIt+"!!!");
			}
		}
		return medicines;
	}

	@Override
	public Medicine getMedicineByName(String name) {
		for (Medicine medicineIt : getAll()) {
			if (medicineIt.getMedicineName().toUpperCase().equals(name.toUpperCase())) return medicineIt;
		}
		return null;
	}

	@Override
	public MedicineInfo getMedicineInfoByMedicineName(String medicineName) {
		return (MedicineInfo) getAll().stream().filter(medicine -> medicine.getMedicineName().equals(medicineName)).findFirst().orElse(null);
	}
}

package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.EPrescriptionDTO;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.domain.medicine.StatusOfEPrescription;
import com.isa.pharmacies_system.domain.medicine.TypeOfItem;
import com.isa.pharmacies_system.domain.storage.PharmacyStorage;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.repository.IEPrescriptionItemRepository;
import com.isa.pharmacies_system.repository.IEPrescriptionRepository;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.repository.IPatientRepository;
import com.isa.pharmacies_system.repository.IPharmacyRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageItemRepository;
import com.isa.pharmacies_system.repository.IPharmacyStorageRepository;
import com.isa.pharmacies_system.service.iService.IEPrescriptionService;

@Service
public class EPrescriptionService implements IEPrescriptionService {

    private IEPrescriptionRepository ePrescriptionRepository;
    private IPatientRepository iPatientRepository;
    private IMedicineRepository iMedicineRepository;
    private IEPrescriptionItemRepository iEPrescriptionItemRepository;
    private IPharmacyRepository iPharmacyRepository;
    private IPharmacyStorageRepository iPharmacyStorageRepository;
    private IPharmacyStorageItemRepository iPharmacyStorageItemRepository;
    
    public EPrescriptionService(IEPrescriptionRepository ePrescriptionRepository, IPatientRepository iPatientRepository, IMedicineRepository iMedicineRepository, IEPrescriptionItemRepository iEPrescriptionItemRepository, IPharmacyRepository iPharmacyRepository, IPharmacyStorageRepository iPharmacyStorageRepository, IPharmacyStorageItemRepository iPharmacyStorageItemRepository) {
        this.ePrescriptionRepository = ePrescriptionRepository;
        this.iPatientRepository = iPatientRepository;
        this.iMedicineRepository = iMedicineRepository;
        this.iEPrescriptionItemRepository = iEPrescriptionItemRepository;
        this.iPharmacyRepository = iPharmacyRepository;
        this.iPharmacyStorageRepository = iPharmacyStorageRepository;
        this.iPharmacyStorageItemRepository=iPharmacyStorageItemRepository;
    }

    @Override
    public List<EPrescriptionItem> getAllEPrescriptionItemForEPrescription(Long ePrescriptionId){
        return ePrescriptionRepository.getAllEPrescriptionItemForEPrescription(ePrescriptionId);
    }

    @Override
    public List<EPrescriptionDTO> sortByEPrescriptionByDate(List<EPrescriptionDTO> ePrescriptions, boolean asc) {
        if(asc){
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getLocalDateTime));
        }else{
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getLocalDateTime).reversed());
        }
        return ePrescriptions;
    }

    @Override
    public List<EPrescriptionDTO> sortByEPrescriptionByStatus(List<EPrescriptionDTO> ePrescriptions, boolean asc) {
        if(asc){
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getStatusOfEPrescription));
        }else{
            Collections.sort(ePrescriptions, Comparator.comparing(EPrescriptionDTO::getStatusOfEPrescription).reversed());
        }
        return ePrescriptions;
    }

	@Override
	public EPrescription createEPrescription(long patientId) {
		EPrescription ePrescription = new EPrescription();
		ePrescription.setCreationDate(LocalDateTime.now());
		ePrescription.setePrescriptionItems(new HashSet<EPrescriptionItem>());
		ePrescription.setPatientForEPrescription(iPatientRepository.findById(patientId).orElse(null));
		ePrescription.setPharmacyForEPrescription(null);
		ePrescription.setStatusOfEPrescription(StatusOfEPrescription.NEW);
		return ePrescriptionRepository.save(ePrescription);
	}
	
	@Override
	public EPrescription updateEPrescriptionToProcessed(long ePrescriptionId, long pharmacyId) {
		EPrescription ePrescription =ePrescriptionRepository.findById(ePrescriptionId).orElse(null);
		ePrescription.setPharmacyForEPrescription(iPharmacyRepository.findById(pharmacyId).orElse(null));
		ePrescription.setStatusOfEPrescription(StatusOfEPrescription.PROCESSED);
		return ePrescriptionRepository.save(ePrescription);
	}
	
	@Override
	public void updateAmountForMedicineInPharmacyStorage(long ePrescriptionId) {
		EPrescription ePrescription =ePrescriptionRepository.findById(ePrescriptionId).orElse(null);
		for (EPrescriptionItem ePrescriptionItem: ePrescription.getePrescriptionItems()) {
			updateStateOfMedicineInPharmacyStorage(ePrescriptionItem,ePrescription.getPharmacyForEPrescription().getId());
		}
	}
	
	private void updateStateOfMedicineInPharmacyStorage(EPrescriptionItem ePrescriptionItem,long pharmacyId) {
		PharmacyStorage pharmacyStorage = iPharmacyStorageRepository.findAll().stream().filter(pharmacyStorageIt -> pharmacyStorageIt.getPharmacy().getId()==pharmacyId).findFirst().orElse(null);
		for (PharmacyStorageItem pharmacyStorageItem : pharmacyStorage.getPharmacyStorageItems()) {
			if (pharmacyStorageItem.getMedicineItem().getMedicineName().equals(ePrescriptionItem.getMedicineItem().getMedicineName())) {
				pharmacyStorageItem.setMedicineAmount(pharmacyStorageItem.getMedicineAmount()-ePrescriptionItem.getMedicineAmount());
				iPharmacyStorageItemRepository.save(pharmacyStorageItem);
			}
		}
	}
	
	@Override
	public List<EPrescriptionItem> getListOfEPrescriptionItemsFromString(long ePrescriptionId, String itemsInString) {
		List<EPrescriptionItem> ePrescriptionItems = new ArrayList<EPrescriptionItem>();
		String[] splitedItemsInString = itemsInString.split(";");
		for (String item : splitedItemsInString) {
			String[] splitedItemOfItemsInString = item.split("-");
			EPrescriptionItem ePrescriptionItem = new EPrescriptionItem();
			ePrescriptionItem.setePrescriton(ePrescriptionRepository.findById(ePrescriptionId).orElse(null));
			ePrescriptionItem.setMedicineAmount(Long.parseLong(splitedItemOfItemsInString[1]));
			ePrescriptionItem.setTypeOfItem(TypeOfItem.EPrescription_item);
			ePrescriptionItem.setMedicineItem(iMedicineRepository.findMedicineByName(splitedItemOfItemsInString[0]));
			ePrescriptionItems.add(ePrescriptionItem);
			iEPrescriptionItemRepository.save(ePrescriptionItem);
		}
		return ePrescriptionItems;
	}
}

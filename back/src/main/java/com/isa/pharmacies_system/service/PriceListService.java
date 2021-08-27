package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import com.isa.pharmacies_system.repository.IPriceListRepository;
import com.isa.pharmacies_system.service.iService.IPriceListService;

@Service
public class PriceListService implements IPriceListService {

    private IPriceListRepository priceListRepository;

    @Autowired
    public PriceListService(IPriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public PriceListForAppointmentDTO findPriceListByPharmacyId(Long pharmacyId){
        return priceListRepository.getPriceListForAppointmentByPharmacyId(pharmacyId);
    }
    
    @Override
    public MedicinePrice getPriceForMedicineInPharmacy(Medicine medicine, Pharmacy pharmacy) {
    	return medicine.getMedicinePrices().stream()
    			.filter(medicinePrice -> (medicinePrice.getMedicinePriceStartTime().isBefore(LocalDateTime.now()) && 
    					medicinePrice.getMedicnePriceEndTime().isAfter(LocalDateTime.now()) && 
    					medicinePrice.getPriceListForMedicine().getPharmacy().getId() == pharmacy.getId()))
    			.findFirst()
    			.orElse(null);
    }

    //#1[3.16]Korak1
    @Override
    public PharmacyDTO addPriceListToPharmacyDTO(PharmacyDTO pharmacyDTO){
        PriceListForAppointmentDTO priceListForAppointmentDTO = findPriceListByPharmacyId(pharmacyDTO.getId());
        pharmacyDTO.setPriceListForAppointmentDTO(priceListForAppointmentDTO);
        return pharmacyDTO;
    }

}

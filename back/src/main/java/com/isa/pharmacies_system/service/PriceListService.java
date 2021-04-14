package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.DTO.PharmacyDTO;
import com.isa.pharmacies_system.DTO.PriceListForAppointmentDTO;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.pharmacy.PriceList;
import com.isa.pharmacies_system.repository.IPriceListRepository;
import com.isa.pharmacies_system.service.iService.IPriceListService;
import org.springframework.stereotype.Service;

@Service
public class PriceListService implements IPriceListService {

    private IPriceListRepository priceListRepository;


    public PriceListService(IPriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public PriceListForAppointmentDTO findPriceListByPharmacyId(Long pharmacyId){
        return priceListRepository.getPriceListForAppointmentByPharmacyId(pharmacyId);
    }

    //#1[3.16]Korak1
    @Override
    public PharmacyDTO addPriceListToPharmacyDTO(PharmacyDTO pharmacyDTO){
        PriceListForAppointmentDTO priceListForAppointmentDTO = findPriceListByPharmacyId(pharmacyDTO.getId());
        pharmacyDTO.setPriceListForAppointmentDTO(priceListForAppointmentDTO);
        return pharmacyDTO;
    }

}

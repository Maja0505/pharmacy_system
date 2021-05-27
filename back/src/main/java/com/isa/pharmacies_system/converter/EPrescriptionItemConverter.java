package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.EPrescriptionDTO;
import com.isa.pharmacies_system.DTO.EPrescriptionItemDTO;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.EPrescriptionItem;
import com.isa.pharmacies_system.domain.medicine.Medicine;

import java.util.ArrayList;
import java.util.List;

public class EPrescriptionItemConverter {

    public EPrescriptionItemConverter() {
    }

    public List<EPrescriptionItemDTO> convertEPrescriptionItemsToEPrescriptionItemDTOS(List<EPrescriptionItem> ePrescriptionItems){

        List<EPrescriptionItemDTO> ePrescriptionItemDTOS = new ArrayList<>();

        for (EPrescriptionItem ePrescriptionItem:ePrescriptionItems) {
            ePrescriptionItemDTOS.add(convertEPrescriptionItemToEPrescriptionItemDTO(ePrescriptionItem));
        }
        return ePrescriptionItemDTOS;
    }

    public EPrescriptionItemDTO convertEPrescriptionItemToEPrescriptionItemDTO(EPrescriptionItem ePrescriptionItem){
        EPrescriptionItemDTO ePrescriptionItemDTO = new EPrescriptionItemDTO();
        ePrescriptionItemDTO.setMedicineAmount(ePrescriptionItem.getMedicineAmount());
        Medicine medicine = ePrescriptionItem.getMedicineItem();
        ePrescriptionItemDTO.setMedicineId(medicine.getId());
        ePrescriptionItemDTO.setMedicineName(medicine.getMedicineName());
        return ePrescriptionItemDTO;
    }


    public List<EPrescriptionDTO> convertEPrescriptionToEPrescriptionDTOS(List<EPrescription> ePrescriptions){

        List<EPrescriptionDTO> ePrescriptionDTOS = new ArrayList<>();

        for (EPrescription ePrescription:ePrescriptions) {
            ePrescriptionDTOS.add(convertEPrescriptionToEPrescriptionDTO(ePrescription));
        }
        return ePrescriptionDTOS;
    }

    public EPrescriptionDTO convertEPrescriptionToEPrescriptionDTO(EPrescription ePrescription){
        EPrescriptionDTO ePrescriptionDTO = new EPrescriptionDTO();
        ePrescriptionDTO.setId(ePrescription.getId());
        ePrescriptionDTO.setLocalDateTime(ePrescription.getCreationDate());
        ePrescriptionDTO.setStatusOfEPrescription(ePrescription.getStatusOfEPrescription());

        return ePrescriptionDTO;
    }


    }

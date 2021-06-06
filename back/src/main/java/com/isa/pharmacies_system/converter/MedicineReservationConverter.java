package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Patient;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MedicineReservationConverter {

    public MedicineReservationConverter() {
    }

    //#1[3.19]
    public MedicineReservation convertMedicineReservationDTOToMedicineReservarvation(MedicineReservationDTO medicineReservationDTO, Patient patient, Medicine medicine, Pharmacy pharmacy){
        MedicineReservation medicineReservation = new MedicineReservation();
        medicineReservation.setReservedMedicine(medicine);
        medicineReservation.setPatientForMedicineReservation(patient);
        medicineReservation.setPharmacyForMedicineReservation(pharmacy);
        medicineReservation.setStatusOfMedicineReservation(StatusOfMedicineReservation.CREATED);
        medicineReservation.setDateOfTakingMedicine(medicineReservationDTO.getDateOfTakingMedicine());
        return medicineReservation;
    }

    public List<MedicineReservationInfoDTO> convertMedicineReservationListToMedicineReservationInfoDTOS(Set<MedicineReservation> medicineReservations){
        List<MedicineReservationInfoDTO> medicineReservationInfoDTOS = new ArrayList<>();
        for (MedicineReservation medicineReservation:medicineReservations) {
                medicineReservationInfoDTOS.add(convertMedicineReservationToMedicineReservationInfoDTO(medicineReservation));
        }
        return medicineReservationInfoDTOS;
    }

    public MedicineReservationInfoDTO convertMedicineReservationToMedicineReservationInfoDTO(MedicineReservation medicineReservation){
        MedicineReservationInfoDTO medicineReservationInfoDTO = new MedicineReservationInfoDTO();
        medicineReservationInfoDTO.setReservationId(medicineReservation.getId());
        medicineReservationInfoDTO.setMedicineId(medicineReservation.getReservedMedicine().getId());
        medicineReservationInfoDTO.setPharmacyId(medicineReservation.getPharmacyForMedicineReservation().getId());
        medicineReservationInfoDTO.setMedicineName(medicineReservation.getReservedMedicine().getMedicineName());
        medicineReservationInfoDTO.setPharmacyName(medicineReservation.getPharmacyForMedicineReservation().getPharmacyName());
        medicineReservationInfoDTO.setStatusOfMedicineReservation(medicineReservation.getStatusOfMedicineReservation());
        medicineReservationInfoDTO.setTakingDate(medicineReservation.getDateOfTakingMedicine());
        return medicineReservationInfoDTO;
    }


    //Nemanja
    public MedicineReservationForTakingDTO convertMedicineReservationToMedicineReservationForTakingDTO(MedicineReservation medicineReservation){
        MedicineReservationForTakingDTO medicineReservationForTakingDTO = new MedicineReservationForTakingDTO();
        medicineReservationForTakingDTO.setMedicineReservationId(medicineReservation.getId());

        medicineReservationForTakingDTO.setDateOfTakingMedicine(medicineReservation.getDateOfTakingMedicine());
        medicineReservationForTakingDTO.setStatusOfMedicineReservation(medicineReservation.getStatusOfMedicineReservation());
        Patient patient = medicineReservation.getPatientForMedicineReservation();
        if(patient != null){
            setPatientInfo(medicineReservationForTakingDTO,patient);
        }
        Medicine medicine = medicineReservation.getReservedMedicine();
        if(medicine != null){
            setMedicineInfo(medicineReservationForTakingDTO,medicine);
        }
        return  medicineReservationForTakingDTO;
    }

    //Nemanja
    private void setMedicineInfo(MedicineReservationForTakingDTO medicineReservationForTakingDTO, Medicine medicine) {
        medicineReservationForTakingDTO.setMedicineName(medicine.getMedicineName());
        medicineReservationForTakingDTO.setMedicineCode(medicine.getMedicineCode());
        medicineReservationForTakingDTO.setFormOfMedicine(medicine.getFormOfMedicine());
        medicineReservationForTakingDTO.setTypeOfMedicine(medicine.getTypeOfMedicine());
        medicineReservationForTakingDTO.setManufacturerOfMedicine(medicine.getManufacturerOfMedicine());

    }

    //Nemanja
    private void setPatientInfo(MedicineReservationForTakingDTO medicineReservationForTakingDTO, Patient patient) {
        medicineReservationForTakingDTO.setPatientFirstName(patient.getFirstName());
        medicineReservationForTakingDTO.setPatientLastName(patient.getLastName());
        medicineReservationForTakingDTO.setPatientPhoneNumber(patient.getPhoneNumber());
        medicineReservationForTakingDTO.setPatientEmail(patient.getEmail());
    }

}

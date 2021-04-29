package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.MedicineReservationDTO;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.StatusOfMedicineReservation;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.user.Patient;

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
}

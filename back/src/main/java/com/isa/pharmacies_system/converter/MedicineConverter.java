package com.isa.pharmacies_system.converter;

import java.util.HashSet;
import java.util.Set;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.domain.medicine.Item;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicinePrice;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.medicine.PrescriptionRegime;
import com.isa.pharmacies_system.domain.rating.MedicineRating;

public class MedicineConverter {
	
	public MedicineConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public Medicine convertMedicineNewDTOToMedicine(MedicineNewDTO medicineNewDTO) {
		Medicine medicine = new Medicine();
		medicine.setMedicineName(medicineNewDTO.getName());
		medicine.setMedicineCode(medicineNewDTO.getCode());
		//medicine.setAlternativeMedicines(medicineNe);// setovati u servisu ili "kontroleru"
		medicine.setFormOfMedicine(medicineNewDTO.getForm());
		medicine.setIngredients(medicineNewDTO.getIngredients());
		medicine.setItems(new HashSet<Item>());
		medicine.setManufacturerOfMedicine(medicineNewDTO.getManufacturerName());
		medicine.setMedicineAverageRating(0);
		medicine.setMedicinePrices(new HashSet<MedicinePrice>());
		medicine.setMedicineRatings(new HashSet<MedicineRating>());
		medicine.setMedicineReservations(new HashSet<MedicineReservation>());
		medicine.setNotes(medicineNewDTO.getNotes());
		medicine.setPrescriptionRegime(medicineNewDTO.getOnRecept() ? PrescriptionRegime.With_prescription : PrescriptionRegime.Without_prescription);
		medicine.setTypeOfMedicine(medicineNewDTO.getType());
		return medicine;
	}
	
	public MedicineNewDTO convertMedicineToMedicineNewDTO(Medicine medicine) {
		MedicineNewDTO medicineNewDTO = new MedicineNewDTO();
		medicineNewDTO.setCode(medicine.getMedicineCode());
		medicineNewDTO.setForm(medicine.getFormOfMedicine());
		medicineNewDTO.setIngredients(medicine.getIngredients());
		medicineNewDTO.setManufacturerName(medicine.getManufacturerOfMedicine());
		medicineNewDTO.setName(medicine.getMedicineName());
		medicineNewDTO.setNamesOfAlternativeMedicines(null);
		medicineNewDTO.setOnRecept(medicine.getPrescriptionRegime()==PrescriptionRegime.With_prescription ? true : false);
		medicineNewDTO.setNotes(medicine.getNotes());
		medicineNewDTO.setType(medicine.getTypeOfMedicine());
		return medicineNewDTO;
	}
}

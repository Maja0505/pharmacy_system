package com.isa.pharmacies_system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.MedicineNewDTO;
import com.isa.pharmacies_system.converter.MedicineConverter;
import com.isa.pharmacies_system.domain.medicine.Medicine;
import com.isa.pharmacies_system.domain.medicine.MedicineInfo;
import com.isa.pharmacies_system.repository.IMedicineInfoRepository;
import com.isa.pharmacies_system.repository.IMedicineRepository;
import com.isa.pharmacies_system.service.iService.IMedicineService;

@Service
public class MedicineService implements IMedicineService {

    private IMedicineRepository medicineRepository;
    private IMedicineInfoRepository iMedicineInfoRepository;
    private MedicineConverter medicineConverter;
    
    @Autowired
    public MedicineService(IMedicineRepository medicineRepository, IMedicineInfoRepository iMedicineInfoRepository) {
        this.medicineRepository = medicineRepository;
        this.iMedicineInfoRepository = iMedicineInfoRepository;
        this.medicineConverter = new MedicineConverter();
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
}

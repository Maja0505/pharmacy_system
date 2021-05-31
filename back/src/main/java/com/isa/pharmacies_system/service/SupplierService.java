package com.isa.pharmacies_system.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.DTO.SupplierNewDTO;
import com.isa.pharmacies_system.converter.SupplierConverter;
import com.isa.pharmacies_system.domain.storage.Storage;
import com.isa.pharmacies_system.domain.storage.SupplierStorage;
import com.isa.pharmacies_system.domain.storage.SupplierStorageItem;
import com.isa.pharmacies_system.domain.storage.TypeOfStorage;
import com.isa.pharmacies_system.domain.user.Supplier;
import com.isa.pharmacies_system.repository.IStorageRepository;
import com.isa.pharmacies_system.repository.ISupplierRepository;
import com.isa.pharmacies_system.repository.ISupplierStorageRepository;
import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.ISupplierService;

@Service
public class SupplierService implements ISupplierService {

	private ISupplierRepository iSupplierRepository;
	private IUserRepository iUserRepository;
	private IStorageRepository iStorageRepository;
	private ISupplierStorageRepository iSupplierStorageRepository;
	private SupplierConverter supplierConverter;
	
	@Autowired
	public SupplierService(ISupplierRepository iSupplierRepository, IUserRepository iUserRepository,IStorageRepository iStorageRepository, ISupplierStorageRepository iSupplierStorageRepository) {
		this.iSupplierRepository = iSupplierRepository;
		this.iUserRepository = iUserRepository;
		this.iStorageRepository = iStorageRepository;
		this.iSupplierStorageRepository = iSupplierStorageRepository;
		this.supplierConverter = new SupplierConverter();
	}
	
	@Override
	public Supplier create(SupplierNewDTO supplierNewDTO) throws Exception {
		Supplier supplier = iSupplierRepository.save(supplierConverter.convertSupplierNewDTOToSupplier(supplierNewDTO));
		addStorageForCreatedSupplier(supplier);
		return supplier;
	}

	private void addStorageForCreatedSupplier(Supplier supplier) {
		SupplierStorage supplierStorage = new SupplierStorage();
		supplierStorage.setSupplier(supplier);
		supplierStorage.setSupplierStorageItems(new HashSet<SupplierStorageItem>());
		supplierStorage.setTypeOfStorage(TypeOfStorage.Supplier_storage);
		iSupplierStorageRepository.save(supplierStorage);
		iStorageRepository.save((Storage) supplierStorage);
	}

	@Override
	public List<Supplier> getAll() {
		return iSupplierRepository.findAll();
	}

	@Override
	public Supplier getById(Long id) throws Exception {
		Supplier supplier = iSupplierRepository.findById(id).orElse(null);
		if (supplier == null) {
			throw new Exception("Don't exist pharmacy with id "+id+"!");
		}
		return supplier;
	}

}

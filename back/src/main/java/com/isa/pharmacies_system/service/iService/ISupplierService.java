package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.DTO.SupplierNewDTO;
import com.isa.pharmacies_system.domain.user.Supplier;

public interface ISupplierService {
	Supplier create(SupplierNewDTO supplierNewDTO) throws Exception;
	List<Supplier> getAll();
	Supplier getById(Long id) throws Exception;
}

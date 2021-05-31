package com.isa.pharmacies_system.converter;

import java.util.HashSet;

import com.isa.pharmacies_system.DTO.SupplierNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Offer;
import com.isa.pharmacies_system.domain.user.Supplier;
import com.isa.pharmacies_system.domain.user.TypeOfUser;

public class SupplierConverter {
	public SupplierConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public Supplier convertSupplierNewDTOToSupplier(SupplierNewDTO supplierNewDTO) {
		Supplier supplier = new Supplier();
		supplier.setEmail(supplierNewDTO.getEmail());
		supplier.setFirstName(supplierNewDTO.getFirstName());
		supplier.setLastName(supplierNewDTO.getLastName());
		supplier.setListOfOffers(new HashSet<Offer>());
		supplier.setPassword(supplierNewDTO.getPassword());
		supplier.setPhoneNumber(supplierNewDTO.getPhoneNumber());
		supplier.setTypeOfUser(TypeOfUser.Supplier);
		supplier.setUserAddress(supplierNewDTO.getResidentialAddress());
		return supplier;
	}
	
	public SupplierNewDTO convertSupplierToSupplierNewDTO(Supplier supplier) {
		SupplierNewDTO supplierNewDTO = new SupplierNewDTO();
		supplierNewDTO.setEmail(supplier.getEmail());
		supplierNewDTO.setFirstName(supplier.getFirstName());
		supplierNewDTO.setLastName(supplier.getLastName());
		supplierNewDTO.setPassword(supplier.getPassword());
		supplierNewDTO.setPhoneNumber(supplier.getPhoneNumber());
		supplierNewDTO.setResidentialAddress(supplier.getUserAddress());
		return supplierNewDTO;
	}
}

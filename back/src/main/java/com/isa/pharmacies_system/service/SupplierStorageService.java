package com.isa.pharmacies_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.pharmacy.Order;
import com.isa.pharmacies_system.domain.pharmacy.OrderItem;
import com.isa.pharmacies_system.domain.storage.SupplierStorage;
import com.isa.pharmacies_system.domain.storage.SupplierStorageItem;
import com.isa.pharmacies_system.repository.IOrderRepository;
import com.isa.pharmacies_system.repository.ISupplierStorageRepository;
import com.isa.pharmacies_system.service.iService.ISupplierStorageService;

@Service
public class SupplierStorageService implements ISupplierStorageService {
	private ISupplierStorageRepository iSupplierStorageRepository;
	private IOrderRepository iOrderRepository;
	
	@Autowired
	public SupplierStorageService(ISupplierStorageRepository iSupplierStorageRepository, IOrderRepository iOrderRepository) {
		this.iSupplierStorageRepository = iSupplierStorageRepository;
		this.iOrderRepository = iOrderRepository;
	}
	
	@Override
	public boolean checkMedicinesHaveForOrder(long orderId, long supplierId) {
		SupplierStorage supplierStorage= iSupplierStorageRepository.findAll().stream().filter(supplierStorageIt -> supplierStorageIt.getSupplier().getId()==supplierId).findFirst().orElse(null);
		Order order = iOrderRepository.findById(orderId).orElse(null);
		for (OrderItem orderItemIt : order.getOrderItems()) {
			if (!checkOrderItemHaveInStorage(supplierStorage, orderItemIt)) return false;
		}
		return true;
	}

	private boolean checkOrderItemHaveInStorage(SupplierStorage supplierStorage, OrderItem orderItemIt) {
		for (SupplierStorageItem supplierStorageItemIt : supplierStorage.getSupplierStorageItems()) {
			if (supplierStorageItemIt.getMedicineItem().getMedicineName().equals(orderItemIt.getMedicineItem().getMedicineName()) && supplierStorageItemIt.getMedicineAmount()>=0) return true;
			
		}
		return false;
	}

	
}

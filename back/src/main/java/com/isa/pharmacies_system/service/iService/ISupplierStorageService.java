package com.isa.pharmacies_system.service.iService;

public interface ISupplierStorageService {
	boolean checkMedicinesHaveForOrder(long orderId, long supplierId);
}

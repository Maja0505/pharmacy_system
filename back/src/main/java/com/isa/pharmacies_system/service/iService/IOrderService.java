package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.pharmacy.Order;

public interface IOrderService {
	List<Order> GetAllNotFinishedOrders();
	Order GetById(long id);
}

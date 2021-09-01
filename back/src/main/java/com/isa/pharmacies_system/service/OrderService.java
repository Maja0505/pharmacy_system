package com.isa.pharmacies_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.pharmacy.Order;
import com.isa.pharmacies_system.repository.IOrderRepository;
import com.isa.pharmacies_system.service.iService.IOrderService;

@Service
public class OrderService implements IOrderService {
	
	private IOrderRepository iOrderRepository;
	
	@Autowired	
	public OrderService() {
		this.iOrderRepository = iOrderRepository;
	}

	@Override
	public List<Order> GetAllNotFinishedOrders() {
		return iOrderRepository.findAll().stream().filter(order -> order.getOrderEndDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());
	}

	@Override
	public Order GetById(long id) {
		return iOrderRepository.findById(id).orElse(null);
	}
	
}

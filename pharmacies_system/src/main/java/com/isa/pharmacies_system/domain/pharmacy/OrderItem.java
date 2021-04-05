package com.isa.pharmacies_system.domain.pharmacy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.medicine.Item;

@Entity
@Table(name="order_items")
public class OrderItem extends Item {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Order orderWithItem;
	
	public OrderItem() {
		
	}

	public OrderItem(Order orderWithItem) {
		super();
		this.orderWithItem = orderWithItem;
	}

	public Order getOrderWithItem() {
		return orderWithItem;
	}

	public void setOrderWithItem(Order orderWithItem) {
		this.orderWithItem = orderWithItem;
	}
	
}

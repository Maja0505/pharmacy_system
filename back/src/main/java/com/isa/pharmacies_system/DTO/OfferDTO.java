package com.isa.pharmacies_system.DTO;

public class OfferDTO {
	private long id;
	private double fullPrice;
	private String deliveryDate;
	private long supplierId;
	private long orderId;

	public OfferDTO() {
		// TODO Auto-generated constructor stub
	}

	public OfferDTO(long id,double fullPrice, String deliveryDate, long supplierId, long orderId) {
		super();
		this.id = id;
		this.fullPrice = fullPrice;
		this.deliveryDate = deliveryDate;
		this.supplierId = supplierId;
		this.orderId = orderId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}

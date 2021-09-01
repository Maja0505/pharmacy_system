package com.isa.pharmacies_system.DTO;

public class PharmacistComplaintDTO {
	private long patientId;
	private long pharmacistId;
	private String content;
	public PharmacistComplaintDTO() {
		// TODO Auto-generated constructor stub
	}
	public PharmacistComplaintDTO(long patientId, long pharmacistId, String content) {
		super();
		this.patientId = patientId;
		this.pharmacistId = pharmacistId;
		this.content = content;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getPharmacistId() {
		return pharmacistId;
	}
	public void setPharmacistId(long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

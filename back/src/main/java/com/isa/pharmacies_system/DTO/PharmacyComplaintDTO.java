package com.isa.pharmacies_system.DTO;

public class PharmacyComplaintDTO {
	private long pharmacyId;
	private long patientId;
	private String content;

	public PharmacyComplaintDTO() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyComplaintDTO(long pharmacyId, long patientId, String content) {
		super();
		this.pharmacyId = pharmacyId;
		this.patientId = patientId;
		this.content = content;
	}

	public long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

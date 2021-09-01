package com.isa.pharmacies_system.DTO;

public class PharmacyComplaintResponseDTO {
	private String complaintResponseContent;
	private long systemAdminId;
	private long pharmacyComplaintId;

	public PharmacyComplaintResponseDTO(String complaintResponseContent, long systemAdminId, long pharmacyComplaintId) {
		super();
		this.complaintResponseContent = complaintResponseContent;
		this.systemAdminId = systemAdminId;
		this.pharmacyComplaintId = pharmacyComplaintId;
	}

	public PharmacyComplaintResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getComplaintResponseContent() {
		return complaintResponseContent;
	}

	public void setComplaintResponseContent(String complaintResponseContent) {
		this.complaintResponseContent = complaintResponseContent;
	}

	public long getSystemAdminId() {
		return systemAdminId;
	}

	public void setSystemAdminId(long systemAdminId) {
		this.systemAdminId = systemAdminId;
	}

	public long getPharmacyComplaintId() {
		return pharmacyComplaintId;
	}

	public void setPharmacyComplaintId(long pharmacyComplaintId) {
		this.pharmacyComplaintId = pharmacyComplaintId;
	}

}

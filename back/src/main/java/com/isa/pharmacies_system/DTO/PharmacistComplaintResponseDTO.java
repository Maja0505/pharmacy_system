package com.isa.pharmacies_system.DTO;

public class PharmacistComplaintResponseDTO {
	private String complaintResponseContent;
	private long systemAdminId;
	private long pharmacistComplaintId;
	
	public PharmacistComplaintResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public PharmacistComplaintResponseDTO(String complaintResponseContent, long systemAdminId,
			long pharmacistComplaintId) {
		super();
		this.complaintResponseContent = complaintResponseContent;
		this.systemAdminId = systemAdminId;
		this.pharmacistComplaintId = pharmacistComplaintId;
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

	public long getPharmacistComplaintId() {
		return pharmacistComplaintId;
	}

	public void setPharmacistComplaintId(long pharmacistComplaintId) {
		this.pharmacistComplaintId = pharmacistComplaintId;
	}
	
}

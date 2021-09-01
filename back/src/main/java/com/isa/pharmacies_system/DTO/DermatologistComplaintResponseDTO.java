package com.isa.pharmacies_system.DTO;

public class DermatologistComplaintResponseDTO {
	private String complaintResponseContent;
	private long systemAdminId;
	private long dermatologistComplaintId;
	
	public DermatologistComplaintResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public DermatologistComplaintResponseDTO(String complaintResponseContent, long systemAdminId,
			long dermatologistComplaintId) {
		super();
		this.complaintResponseContent = complaintResponseContent;
		this.systemAdminId = systemAdminId;
		this.dermatologistComplaintId = dermatologistComplaintId;
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

	public long getDermatologistComplaintId() {
		return dermatologistComplaintId;
	}

	public void setDermatologistComplaintId(long dermatologistComplaintId) {
		this.dermatologistComplaintId = dermatologistComplaintId;
	}
	
}

package com.isa.pharmacies_system.DTO;

public class DermatologistComplaintDTO {
	private long patientId;
	private long dermatologistId;
	private String content;
	
	public DermatologistComplaintDTO() {
		// TODO Auto-generated constructor stub
	}

	public DermatologistComplaintDTO(long patientId, long dermatologistId, String content) {
		super();
		this.patientId = patientId;
		this.dermatologistId = dermatologistId;
		this.content = content;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(long dermatologistId) {
		this.dermatologistId = dermatologistId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

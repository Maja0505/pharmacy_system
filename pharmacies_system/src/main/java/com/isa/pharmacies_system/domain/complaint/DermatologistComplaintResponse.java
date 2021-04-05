package com.isa.pharmacies_system.domain.complaint;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dermatologist_complaint_responses")
public class DermatologistComplaintResponse extends ComplaintResponse{
	
	@OneToOne(cascade = CascadeType.ALL)
	private DermatologistComplaint dermatologistComplaint;
	
	public DermatologistComplaintResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public DermatologistComplaintResponse(DermatologistComplaint dermatologistComplaint) {
		super();
		this.dermatologistComplaint = dermatologistComplaint;
	}

	public DermatologistComplaint getDermatologistComplaint() {
		return dermatologistComplaint;
	}

	public void setDermatologistComplaint(DermatologistComplaint dermatologistComplaint) {
		this.dermatologistComplaint = dermatologistComplaint;
	}

}

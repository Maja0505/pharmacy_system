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
@Table(name="pharmacy_complaint_responses")
public class PharmacyComplaintResponse extends ComplaintResponse {
	
	@OneToOne(cascade = CascadeType.ALL)
	private PharmacyComplaint pharmacyComplaint;

	public PharmacyComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public PharmacyComplaintResponse(PharmacyComplaint pharmacyComplaint) {
		super();
		this.pharmacyComplaint = pharmacyComplaint;
	}

	public PharmacyComplaint getPharmacyComplaint() {
		return pharmacyComplaint;
	}

	public void setPharmacyComplaint(PharmacyComplaint pharmacyComplaint) {
		this.pharmacyComplaint = pharmacyComplaint;
	}

}

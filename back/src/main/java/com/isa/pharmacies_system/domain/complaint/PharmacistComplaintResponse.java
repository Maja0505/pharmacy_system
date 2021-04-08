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

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

@Entity
@Table(name="pharmacist_complaint_responses")
public class PharmacistComplaintResponse extends ComplaintResponse {

	@OneToOne(cascade = CascadeType.ALL)
	private PharmacistComplaint pharmacistComplaint;
	
	public PharmacistComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public PharmacistComplaintResponse(PharmacistComplaint pharmacistComplaint) {
		super();
		this.pharmacistComplaint = pharmacistComplaint;
	}


	public PharmacistComplaint getPharmacistComplaint() {
		return pharmacistComplaint;
	}

	public void setPharmacistComplaint(PharmacistComplaint pharmacistComplaint) {
		this.pharmacistComplaint = pharmacistComplaint;
	}
	
}

package com.isa.pharmacies_system.domain.complaint;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.pharmacies_system.domain.user.SystemAdmin;

@Entity
@Table(name="complaint_responses")
@Inheritance(strategy=JOINED)
public class ComplaintResponse {
	@Id
	@SequenceGenerator(name = "mySeqGenComplaintResponse", sequenceName = "mySeqComplaintResponse", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenComplaintResponseComplaintResponse")
	private long id;
	
	@Column(name="complaintResponseContent", unique=false, nullable=false)
	private String complaintResponseContent;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin systemAdminForComplaintResponse;
	
	public ComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public ComplaintResponse(long id, String complaintResponseContent, SystemAdmin systemAdminForComplaintResponse) {
		super();
		this.id = id;
		this.complaintResponseContent = complaintResponseContent;
		this.systemAdminForComplaintResponse = systemAdminForComplaintResponse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComplaintResponseContent() {
		return complaintResponseContent;
	}

	public void setComplaintResponseContent(String complaintResponseContent) {
		this.complaintResponseContent = complaintResponseContent;
	}

	public SystemAdmin getSystemAdminForComplaintResponse() {
		return systemAdminForComplaintResponse;
	}

	public void setSystemAdminForComplaintResponse(SystemAdmin systemAdminForComplaintResponse) {
		this.systemAdminForComplaintResponse = systemAdminForComplaintResponse;
	}
	
}

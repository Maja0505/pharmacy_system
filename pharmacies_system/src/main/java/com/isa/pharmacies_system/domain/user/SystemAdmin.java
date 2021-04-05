package com.isa.pharmacies_system.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.complaint.ComplaintResponse;

@Entity
@Table(name="system_admin")
public class SystemAdmin extends Users{
	
	@OneToMany(mappedBy = "systemAdminForComplaintResponse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ComplaintResponse> systemAdminComplaintResponses = new HashSet<ComplaintResponse>();
	
	public SystemAdmin() {
		
	}

	public SystemAdmin(Set<ComplaintResponse> systemAdminComplaintResponses) {
		super();
		this.systemAdminComplaintResponses = systemAdminComplaintResponses;
	}

	public Set<ComplaintResponse> getSystemAdminComplaintResponses() {
		return systemAdminComplaintResponses;
	}

	public void setSystemAdminComplaintResponses(Set<ComplaintResponse> systemAdminComplaintResponses) {
		this.systemAdminComplaintResponses = systemAdminComplaintResponses;
	}
	
}

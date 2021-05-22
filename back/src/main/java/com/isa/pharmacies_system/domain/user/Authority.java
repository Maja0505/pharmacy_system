package com.isa.pharmacies_system.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="autorizations")
public class Authority implements GrantedAuthority {

	@Id
	@SequenceGenerator(name = "mySeqGenAuthority", sequenceName = "mySeqAuthority", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenAuthority")
    private Long id;
	
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

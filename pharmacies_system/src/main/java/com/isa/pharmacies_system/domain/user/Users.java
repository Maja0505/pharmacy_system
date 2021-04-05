package com.isa.pharmacies_system.domain.user;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.pharmacy.Address;

import javax.persistence.ManyToOne;

@Entity
@Table(name="users")
@Inheritance(strategy=JOINED)
public class Users{

	@Id
	@SequenceGenerator(name = "mySeqGenUsers", sequenceName = "mySeqUsers", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenUsers")
	private long id;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="firstName", unique=false, nullable=false)
	private String firstName;

	@Column(name="lastName", unique=false, nullable=false)
	private String lastName;

	private Address userAddress;
	
	@Column(name="phoneNumber", unique=false, nullable=false)
	private String phoneNumber;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfUser typeOfUser;

	public Users() {
		
	}



	public Users(long id, String email, String password, String firstName, String lastName, Address userAddress,
			String phoneNumber, TypeOfUser typeOfUser) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userAddress = userAddress;
		this.phoneNumber = phoneNumber;
		this.typeOfUser = typeOfUser;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public Address getUserAddress() {
		return userAddress;
	}



	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}



	public TypeOfUser getTypeOfUser() {
		return typeOfUser;
	}



	public void setTypeOfUser(TypeOfUser typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
	
	
	

}

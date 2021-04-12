package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public abstract class UserNewDTO {
	private String email;
	private String firstName;
	private String lastName;
	private Address residentialAddress;
	private String phoneNumber;
	private String password;

	public UserNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserNewDTO(String email, String firstName, String lastName, Address residentialAddress, String phoneNumber,
			String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.residentialAddress = residentialAddress;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Address getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

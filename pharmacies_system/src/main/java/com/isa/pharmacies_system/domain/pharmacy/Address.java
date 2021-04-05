package com.isa.pharmacies_system.domain.pharmacy;

import javax.persistence.*;

import com.isa.pharmacies_system.domain.user.Users;

import java.util.HashSet;
import java.util.Set;

@Embeddable
public class Address {
	
	private String streetName;
	
	private String streetNumber;
	
	private String city;
	
	private String country;
	
	private double longitude;
	
	private double latitude;

	public Address()
	{
		
	}
	

	public Address(long id, String streetName, String streetNumber, String city, String country, double longitude,
			double latitude) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
	}



	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


}

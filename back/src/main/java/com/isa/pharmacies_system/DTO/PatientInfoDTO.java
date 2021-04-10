package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.pharmacy.Address;

public class PatientInfoDTO {

    private long id;

    private String firstName;

    private String lastName;

    private Address userAddress;

    private String phoneNumber;


    public PatientInfoDTO(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

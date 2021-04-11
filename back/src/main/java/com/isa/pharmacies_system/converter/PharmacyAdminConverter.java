package com.isa.pharmacies_system.converter;

import java.util.HashSet;

import com.isa.pharmacies_system.DTO.PharmacyAdminNewDTO;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Order;
import com.isa.pharmacies_system.domain.user.PharmacyAdmin;
import com.isa.pharmacies_system.domain.user.TypeOfUser;

public class PharmacyAdminConverter {
	public PharmacyAdminConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public PharmacyAdmin convertPharmacyAdminNewDTOToPharmacyAdmin(PharmacyAdminNewDTO pharmacyAdminNewDTO) {
		PharmacyAdmin pharmacyAdmin = new PharmacyAdmin();
		pharmacyAdmin.setEmail(pharmacyAdminNewDTO.getEmail());
		pharmacyAdmin.setFirstName(pharmacyAdminNewDTO.getFirstName());
		pharmacyAdmin.setLastName(pharmacyAdminNewDTO.getLastName());
		pharmacyAdmin.setListOfOrders(new HashSet<Order>());
		pharmacyAdmin.setPassword(pharmacyAdminNewDTO.getPassword());
		pharmacyAdmin.setPhoneNumber(pharmacyAdminNewDTO.getPhoneNumber());
		pharmacyAdmin.setTypeOfUser(TypeOfUser.Pharmacy_admin);
		pharmacyAdmin.setUserAddress(new Address(0,pharmacyAdminNewDTO.getStreetName(),pharmacyAdminNewDTO.getStreetNumber(),pharmacyAdminNewDTO.getCity(),pharmacyAdminNewDTO.getCountry(),0.0,0.0));
		return pharmacyAdmin;
	}
	
	public PharmacyAdminNewDTO convertPharmacyAdminToPharmacyAdminNewDTO(PharmacyAdmin pharmacyAdmin) {
		PharmacyAdminNewDTO pharmacyAdminNewDTO = new PharmacyAdminNewDTO();
		pharmacyAdminNewDTO.setCity(pharmacyAdmin.getUserAddress().getCity());
		pharmacyAdminNewDTO.setCountry(pharmacyAdmin.getUserAddress().getCountry());
		pharmacyAdminNewDTO.setEmail(pharmacyAdmin.getEmail());
		pharmacyAdminNewDTO.setFirstName(pharmacyAdmin.getFirstName());
		pharmacyAdminNewDTO.setIdPharmacy(pharmacyAdmin.getPharmacyForPharmacyAdmin().getId());
		pharmacyAdminNewDTO.setLastName(pharmacyAdmin.getLastName());
		pharmacyAdminNewDTO.setPassword(pharmacyAdmin.getPassword());
		pharmacyAdminNewDTO.setPhoneNumber(pharmacyAdmin.getPhoneNumber());
		pharmacyAdminNewDTO.setStreetName(pharmacyAdmin.getUserAddress().getStreetName());
		pharmacyAdminNewDTO.setStreetNumber(pharmacyAdmin.getUserAddress().getStreetNumber());
		return pharmacyAdminNewDTO;
	}
}

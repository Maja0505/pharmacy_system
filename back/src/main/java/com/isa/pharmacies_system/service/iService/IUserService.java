package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.user.Users;

public interface IUserService {
	Users findOne(long id);
    Users findByEmail(String email);
    List<Users> findAll();
    Users save(Users user);
}

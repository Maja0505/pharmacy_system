package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.user.Users;

public interface IUserService {
	Users findOne(long id) throws Exception;
    Users findByEmail(String email) throws Exception;
    List<Users> findAll();
    Users save(Users user);
    Users changePasswordWhenFirstTimeSingUp(String newPassword, String confirmationOfNewPassword,Long id);
}

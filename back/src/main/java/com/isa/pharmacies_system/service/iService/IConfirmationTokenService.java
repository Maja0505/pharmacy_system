package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.user.ConfirmationToken;
import com.isa.pharmacies_system.domain.user.Users;

public interface IConfirmationTokenService {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken save(Users user);
}

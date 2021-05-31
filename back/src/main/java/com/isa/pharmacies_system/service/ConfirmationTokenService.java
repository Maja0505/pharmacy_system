package com.isa.pharmacies_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.user.ConfirmationToken;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IConfirmationTokenRepository;
import com.isa.pharmacies_system.service.iService.IConfirmationTokenService;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {
	
	private IConfirmationTokenRepository iConfirmationTokenRepository;
	
	@Autowired
	public ConfirmationTokenService(IConfirmationTokenRepository iConfirmationTokenRepository) {
		this.iConfirmationTokenRepository = iConfirmationTokenRepository;
	}

	public List<ConfirmationToken> findAll() {
		return iConfirmationTokenRepository.findAll();
	}
	
	@Override
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		for (ConfirmationToken confirmationTokenIt : findAll()) {
			if (confirmationToken.equals(confirmationTokenIt.getConfirmationToken())) {
				return confirmationTokenIt;
			}
		}
		return null;
	}

	@Override
	public ConfirmationToken save(Users user) {
		return iConfirmationTokenRepository.save(new ConfirmationToken(user));
	}

}

package com.isa.pharmacies_system.services;

import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.iservices.IUsersService;
import com.isa.pharmacies_system.repositories.IUsersRepository;

@Service
public class UsersService implements IUsersService {
	
	private IUsersRepository iUsersRepository;
	
	@Autowired
	public UsersService(IUsersRepository iUsersRepository) {
		this.iUsersRepository = iUsersRepository;
	}
}

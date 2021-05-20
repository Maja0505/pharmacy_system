package com.isa.pharmacies_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.IAuthorityService;
import com.isa.pharmacies_system.service.iService.IUserService;


@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IAuthorityService iAuthorityService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository,IAuthorityService iAuthorityService,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.iAuthorityService = iAuthorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users findOne(long id) throws Exception {
    	Users user = userRepository.findById(id).orElseGet(null);
    	if (user==null) {
    		throw new Exception("User not found!");
    	}
    	return user;
    }

	@Override
	public Users findByEmail(String email) {
		for (Users user : findAll()) {
			if (user.getEmail().equals(email)) 
			{
				//findOne(user.getId());
				return user;
			}
				
		}
		return null;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Users save(Users user) {
		return userRepository.save(user);
	}
}

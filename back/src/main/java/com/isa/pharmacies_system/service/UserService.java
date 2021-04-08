package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.repository.IUserRepository;
import com.isa.pharmacies_system.service.iService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

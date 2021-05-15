package com.isa.pharmacies_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.user.Authority;
import com.isa.pharmacies_system.repository.IAuthorityRepository;
import com.isa.pharmacies_system.service.iService.IAuthorityService;

@Service
public class AuthorityService implements IAuthorityService {
	
	private IAuthorityRepository iAuthorityRepository;
	
	@Autowired
	public AuthorityService(IAuthorityRepository iAuthorityRepository) {
		this.iAuthorityRepository = iAuthorityRepository;
	}

	@Override
	public List<Authority> findById(Long id) {
		List<Authority> authorities = new ArrayList<Authority>();
		Authority authority = iAuthorityRepository.findById(id).get();
		authorities.add(authority);
		return authorities;
	}

	@Override
	public List<Authority> findByName(String name) {
		List<Authority> authorities = new ArrayList<Authority>();
		for (Authority authority : iAuthorityRepository.findAll()) {
			if (authority.getName().equals(name)) {
				authorities.add(authority);
				break;
			}
		}
		return authorities;
	}

}

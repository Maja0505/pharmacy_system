package com.isa.pharmacies_system.service.iService;

import java.util.List;

import com.isa.pharmacies_system.domain.user.Authority;

public interface IAuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByName(String name);
}

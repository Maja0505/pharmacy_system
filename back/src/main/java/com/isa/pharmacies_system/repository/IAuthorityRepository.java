package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.user.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Long>  {

}

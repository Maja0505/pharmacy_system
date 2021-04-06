package com.isa.pharmacies_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.user.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {

}

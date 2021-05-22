package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.user.ConfirmationToken;

public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

}

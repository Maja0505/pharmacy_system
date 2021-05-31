package com.isa.pharmacies_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.pharmacies_system.domain.user.SystemAdmin;

public interface ISystemAdminRepository extends JpaRepository<SystemAdmin,Long>{}

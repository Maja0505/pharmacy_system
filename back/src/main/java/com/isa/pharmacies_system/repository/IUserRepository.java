package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Users,Long> {
}

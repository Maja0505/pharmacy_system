package com.isa.pharmacies_system.repository;

import com.isa.pharmacies_system.domain.user.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;


public interface IPharmacistRepository extends JpaRepository<Pharmacist,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value="5000")})
    Optional<Pharmacist> findLockedById(Long pharmacistId);
}

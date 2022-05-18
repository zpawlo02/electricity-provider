package com.pawelzielinski.repository;

import com.pawelzielinski.model.PowerLimitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlPowerLimitationRepository extends PowerLimitationRepository, JpaRepository<PowerLimitation, Integer> {
}

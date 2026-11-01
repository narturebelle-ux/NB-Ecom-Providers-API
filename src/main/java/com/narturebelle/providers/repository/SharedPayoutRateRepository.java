package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.Role;
import com.narturebelle.providers.model.shared.SharedPayoutRate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedPayoutRateRepository extends JpaRepository<SharedPayoutRate, Long> {

    Optional<SharedPayoutRate> findByProfessionalType(Role professionalType);
}

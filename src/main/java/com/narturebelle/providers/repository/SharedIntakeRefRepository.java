package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedIntakeRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedIntakeRefRepository extends JpaRepository<SharedIntakeRef, Long> {

    boolean existsByUserId(Long userId);
}

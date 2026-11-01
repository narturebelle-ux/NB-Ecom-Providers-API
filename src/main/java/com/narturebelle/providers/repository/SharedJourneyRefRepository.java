package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedJourneyRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedJourneyRefRepository extends JpaRepository<SharedJourneyRef, Long> {

    boolean existsByUserId(Long userId);
}

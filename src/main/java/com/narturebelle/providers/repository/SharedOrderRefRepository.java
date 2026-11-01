package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedOrderRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedOrderRefRepository extends JpaRepository<SharedOrderRef, Long> {

    boolean existsByUserId(Long userId);
}

package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedProviderRepository extends JpaRepository<SharedProvider, Long> {

    Optional<SharedProvider> findByUserId(Long userId);
}

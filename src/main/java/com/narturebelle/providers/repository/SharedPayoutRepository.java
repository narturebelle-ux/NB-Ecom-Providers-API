package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedPayout;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedPayoutRepository extends JpaRepository<SharedPayout, Long> {

    List<SharedPayout> findByProviderIdOrderByCreatedAtDesc(Long providerId);
}

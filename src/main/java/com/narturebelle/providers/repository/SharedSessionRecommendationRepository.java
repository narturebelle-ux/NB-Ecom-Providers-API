package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedSessionRecommendation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedSessionRecommendationRepository extends JpaRepository<SharedSessionRecommendation, Long> {

    List<SharedSessionRecommendation> findTop20ByProviderIdOrderByCreatedAtDesc(Long providerId);
}

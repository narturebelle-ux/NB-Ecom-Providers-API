package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedServiceOffering;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedServiceOfferingRepository extends JpaRepository<SharedServiceOffering, Long> {

    List<SharedServiceOffering> findByProviderIdOrderByCreatedAtDesc(Long providerId);
}

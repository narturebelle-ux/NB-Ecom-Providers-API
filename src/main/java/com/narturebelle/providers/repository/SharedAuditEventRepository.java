package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedAuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedAuditEventRepository extends JpaRepository<SharedAuditEvent, Long> {
}

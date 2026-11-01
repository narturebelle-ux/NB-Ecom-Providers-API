package com.narturebelle.providers.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narturebelle.providers.model.shared.SharedAuditEvent;
import com.narturebelle.providers.repository.SharedAuditEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/** Records append-only audit events in public.audit_events. Never put names, documents or health details in them. */
@Component
public class AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    private static final int MAX_REASON = 255;

    private final SharedAuditEventRepository auditEventRepository;
    private final AuthorizationService authorizationService;
    private final ObjectMapper objectMapper;

    public AuditService(SharedAuditEventRepository auditEventRepository, AuthorizationService authorizationService,
                         ObjectMapper objectMapper) {
        this.auditEventRepository = auditEventRepository;
        this.authorizationService = authorizationService;
        this.objectMapper = objectMapper;
    }

    public void record(String action, String subjectType, String subjectId, String reason) {
        record(action, subjectType, subjectId, reason, null);
    }

    public void record(String action, String subjectType, String subjectId, String reason, Map<String, Object> metadata) {
        SharedAuditEvent event = new SharedAuditEvent();

        authorizationService.currentPrincipalOrEmpty().ifPresent(principal -> {
            event.setActorUserId(principal.userId());
            event.setActorRoles(principal.roles().stream()
                    .map(Enum::name)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.joining(",")));
        });

        event.setAction(action);
        event.setSubjectType(subjectType);
        event.setSubjectId(subjectId);
        event.setReason(reason != null && reason.length() > MAX_REASON ? reason.substring(0, MAX_REASON) : reason);
        event.setRequestId(UUID.randomUUID().toString());

        if (metadata != null && !metadata.isEmpty()) {
            try {
                event.setMetadataJson(objectMapper.writeValueAsString(metadata));
            } catch (Exception e) {
                log.warn("Failed to serialize audit metadata for action {}: {}", action, e.getMessage());
            }
        }

        auditEventRepository.save(event);
    }
}

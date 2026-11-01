package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/** public.audit_events: this service's actions join the same append-only trail. Never put names, documents or health details in it. */
@Entity
@Table(name = "audit_events")
public class SharedAuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime occurredAt;

    private Long actorUserId;

    private String actorRoles;

    @Column(nullable = false)
    private String action;

    private String subjectType;

    private String subjectId;

    private String reason;

    private String requestId;

    @Column(columnDefinition = "text")
    private String metadataJson;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (this.occurredAt == null) this.occurredAt = now;
    }

    public SharedAuditEvent() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }

    public Long getActorUserId() { return actorUserId; }
    public void setActorUserId(Long actorUserId) { this.actorUserId = actorUserId; }

    public String getActorRoles() { return actorRoles; }
    public void setActorRoles(String actorRoles) { this.actorRoles = actorRoles; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getSubjectType() { return subjectType; }
    public void setSubjectType(String subjectType) { this.subjectType = subjectType; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
}

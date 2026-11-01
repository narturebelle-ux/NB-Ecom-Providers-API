package com.narturebelle.providers.model.shared;

import com.narturebelle.providers.model.Role;
import com.narturebelle.providers.model.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/** public.bookings, owned by NB-Ecom-API. This service only cancels upcoming ones when a professional is suspended. */
@Entity
@Table(name = "bookings")
public class SharedBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long serviceOfferingId;

    @Column(nullable = false)
    private Long slotId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role professionalType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false)
    private LocalDateTime scheduledStart;

    @Column(nullable = false)
    private LocalDateTime scheduledEnd;

    private LocalDateTime cancelledAt;

    private Long cancelledByUserId;

    @Column(columnDefinition = "text")
    private String cancelReason;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (this.createdAt == null) this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public SharedBooking() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }

    public Long getServiceOfferingId() { return serviceOfferingId; }
    public void setServiceOfferingId(Long serviceOfferingId) { this.serviceOfferingId = serviceOfferingId; }

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }

    public Role getProfessionalType() { return professionalType; }
    public void setProfessionalType(Role professionalType) { this.professionalType = professionalType; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public LocalDateTime getScheduledStart() { return scheduledStart; }
    public void setScheduledStart(LocalDateTime scheduledStart) { this.scheduledStart = scheduledStart; }

    public LocalDateTime getScheduledEnd() { return scheduledEnd; }
    public void setScheduledEnd(LocalDateTime scheduledEnd) { this.scheduledEnd = scheduledEnd; }

    public LocalDateTime getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(LocalDateTime cancelledAt) { this.cancelledAt = cancelledAt; }

    public Long getCancelledByUserId() { return cancelledByUserId; }
    public void setCancelledByUserId(Long cancelledByUserId) { this.cancelledByUserId = cancelledByUserId; }

    public String getCancelReason() { return cancelReason; }
    public void setCancelReason(String cancelReason) { this.cancelReason = cancelReason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}

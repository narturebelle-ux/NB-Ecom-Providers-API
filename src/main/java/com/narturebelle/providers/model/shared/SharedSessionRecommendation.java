package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/** public.session_product_recommendations, read-only here. The mother's id is deliberately not mapped. */
@Entity
@Table(name = "session_product_recommendations")
public class SharedSessionRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String priority;

    private String note;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public SharedSessionRecommendation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

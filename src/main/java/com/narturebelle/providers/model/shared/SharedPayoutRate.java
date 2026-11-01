package com.narturebelle.providers.model.shared;

import com.narturebelle.providers.model.Role;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** public.payout_rates: the fixed fee per profession. */
@Entity
@Table(name = "payout_rates")
public class SharedPayoutRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Role professionalType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public SharedPayoutRate() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Role getProfessionalType() { return professionalType; }
    public void setProfessionalType(Role professionalType) { this.professionalType = professionalType; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

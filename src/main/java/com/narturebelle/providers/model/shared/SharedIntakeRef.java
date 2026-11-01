package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

/** public.onboarding_intakes, id and owner only: having one marks a mother account. */
@Entity
@Table(name = "onboarding_intakes")
public class SharedIntakeRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    public SharedIntakeRef() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

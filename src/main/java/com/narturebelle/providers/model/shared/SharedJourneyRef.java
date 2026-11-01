package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

/** public.journeys, id and owner only: having one marks a mother account. */
@Entity
@Table(name = "journeys")
public class SharedJourneyRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    public SharedJourneyRef() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

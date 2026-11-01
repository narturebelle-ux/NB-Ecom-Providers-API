package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

/** public.orders, id and owner only: having one marks a mother account. */
@Entity
@Table(name = "orders")
public class SharedOrderRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    public SharedOrderRef() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

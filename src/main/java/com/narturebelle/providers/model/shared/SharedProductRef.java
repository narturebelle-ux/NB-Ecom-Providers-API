package com.narturebelle.providers.model.shared;

import jakarta.persistence.*;

/** public.products, id and name only. */
@Entity
@Table(name = "products")
public class SharedProductRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public SharedProductRef() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

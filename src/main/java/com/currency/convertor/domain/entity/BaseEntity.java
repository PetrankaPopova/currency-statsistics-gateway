package com.currency.convertor.domain.entity;


import javax.persistence.*;
/**
 * The BaseEntity class is an abstract class serving as the base class for JPA entities.
 * It includes a common identifier property (id) mapped as the primary key for all entities.
 *
 * Properties:
 * - id: A unique identifier for each entity, generated using an identity strategy.
 *
 * This class is annotated with JPA annotations:
 * - @MappedSuperclass: Indicates that this class is not an entity itself but should be mapped in the entity hierarchy.
 * - @Id: Specifies the primary key property.
 * - @GeneratedValue: Defines the generation strategy for the primary key (e.g., GenerationType.IDENTITY for auto-increment).
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

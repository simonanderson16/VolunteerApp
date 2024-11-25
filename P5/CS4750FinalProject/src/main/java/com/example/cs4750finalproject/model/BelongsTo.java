package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "belongs_to")
public class BelongsTo {

    @EmbeddedId
    private BelongsToId id;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @MapsId("orgId")
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(name = "role", nullable = false)
    private String role;

    public BelongsTo() {}

    public BelongsTo(Person person, Organization organization, String role) {
        this.person = person;
        this.organization = organization;
        this.role = role;
        this.id = new BelongsToId(person.getPersonId(), organization.getOrgId());
    }

    // Getters and Setters
    public BelongsToId getId() {
        return id;
    }

    public void setId(BelongsToId id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
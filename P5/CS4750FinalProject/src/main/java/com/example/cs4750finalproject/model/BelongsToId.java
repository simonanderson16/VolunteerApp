package com.example.cs4750finalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BelongsToId implements Serializable {

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "org_id")
    private Integer orgId;

    public BelongsToId() {}

    public BelongsToId(Integer personId, Integer orgId) {
        this.personId = personId;
        this.orgId = orgId;
    }

    // Getters and Setters
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BelongsToId)) return false;
        BelongsToId that = (BelongsToId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(orgId, that.orgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, orgId);
    }
}
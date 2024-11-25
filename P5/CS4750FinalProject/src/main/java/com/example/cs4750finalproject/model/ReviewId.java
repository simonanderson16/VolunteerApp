package com.example.cs4750finalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewId implements Serializable {

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "event_id")
    private Integer eventId;

    public ReviewId() {}

    public ReviewId(Integer personId, Integer eventId) {
        this.personId = personId;
        this.eventId = eventId;
    }

    // Getters and Setters
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewId)) return false;
        ReviewId that = (ReviewId) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, eventId);
    }
}

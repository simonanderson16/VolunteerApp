package com.example.cs4750finalproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @EmbeddedId
    private ReviewId id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "title")
    private String title;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "content")
    private String content;

    public Review() {}

    public Review(Integer personId, Integer eventId, String title, Integer rating, String content) {
        this.id = new ReviewId(personId, eventId);
        this.timestamp = LocalDateTime.now(); // Automatically set the current timestamp
        this.title = title;
        this.rating = rating;
        this.content = content;
    }

    // Getters and Setters
    public ReviewId getId() {
        return id;
    }

    public void setId(ReviewId id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

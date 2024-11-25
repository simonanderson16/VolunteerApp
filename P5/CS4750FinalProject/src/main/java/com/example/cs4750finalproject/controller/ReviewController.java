package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Review;
import com.example.cs4750finalproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Review>> getReviewsByPerson(@PathVariable Integer personId) {
        return new ResponseEntity<>(reviewService.getReviewsByPerson(personId), HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Review>> getReviewsByEvent(@PathVariable Integer eventId) {
        return new ResponseEntity<>(reviewService.getReviewsByEvent(eventId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(
            @RequestParam Integer personId,
            @RequestParam Integer eventId,
            @RequestParam String title,
            @RequestParam Integer rating,
            @RequestParam String content) {
        try {
            Review createdReview = reviewService.createReview(personId, eventId, title, rating, content);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Review> updateReview(
            @RequestParam Integer personId,
            @RequestParam Integer eventId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String content) {
        try {
            Review updatedReview = reviewService.updateReview(personId, eventId, title, rating, content);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReview(
            @RequestParam Integer personId,
            @RequestParam Integer eventId) {
        try {
            reviewService.deleteReview(personId, eventId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

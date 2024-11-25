package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.Review;
import com.example.cs4750finalproject.model.ReviewId;
import com.example.cs4750finalproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByPerson(Integer personId) {
        return reviewRepository.findByIdPersonId(personId);
    }

    public List<Review> getReviewsByEvent(Integer eventId) {
        return reviewRepository.findByIdEventId(eventId);
    }

    public Review createReview(Integer personId, Integer eventId, String title, Integer rating, String content) {
        Review review = new Review(personId, eventId, title, rating, content);
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer personId, Integer eventId, String title, Integer rating, String content) {
        ReviewId id = new ReviewId(personId, eventId);
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (title != null) {
            existingReview.setTitle(title);
        }
        if (rating != null) {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }
            existingReview.setRating(rating);
        }
        if (content != null) {
            existingReview.setContent(content);
        }

        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Integer personId, Integer eventId) {
        ReviewId id = new ReviewId(personId, eventId);
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}

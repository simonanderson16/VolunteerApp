package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Review;
import com.example.cs4750finalproject.model.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    List<Review> findByIdPersonId(Integer personId);
    List<Review> findByIdEventId(Integer eventId);
}

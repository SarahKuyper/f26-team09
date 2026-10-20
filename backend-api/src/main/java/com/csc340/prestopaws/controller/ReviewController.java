package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.DTO.ReviewRequest;
import com.csc340.prestopaws.entity.Review;
import com.csc340.prestopaws.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/customers/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@Valid @RequestBody ReviewRequest request) {
        return reviewService.createReview(request);
    }

    @GetMapping("/providers/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/providers/reviews/{id}")
    public Review getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/providers/reviews/item/{itemId}")
    public List<Review> getReviewsForItem(@PathVariable Long itemId) {
        return reviewService.getReviewsForItem(itemId);
    }

    @GetMapping("/providers/reviews/provider/{providerId}")
    public List<Review> getReviewsForProvider(@PathVariable Long providerId) {
        return reviewService.getReviewsForProvider(providerId);
    }
}

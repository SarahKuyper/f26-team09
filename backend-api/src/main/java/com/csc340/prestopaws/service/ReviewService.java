package com.csc340.prestopaws.service;

import com.csc340.prestopaws.DTO.ReviewRequest;
import com.csc340.prestopaws.entity.Buyer;
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.entity.Review;
import com.csc340.prestopaws.repository.BuyerRepository;
import com.csc340.prestopaws.repository.ItemRepository;
import com.csc340.prestopaws.repository.ProviderRepository;
import com.csc340.prestopaws.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BuyerRepository buyerRepository;
    private final ItemRepository itemRepository;
    private final ProviderRepository providerRepository;

    public ReviewService(ReviewRepository reviewRepository, BuyerRepository buyerRepository,
                        ItemRepository itemRepository, ProviderRepository providerRepository) {
        this.reviewRepository = reviewRepository;
        this.buyerRepository = buyerRepository;
        this.itemRepository = itemRepository;
        this.providerRepository = providerRepository;
    }

    @Transactional
    public Review createReview(ReviewRequest request) {
        Buyer buyer = buyerRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found"));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        Provider provider = item.getProvider();

        Review review = new Review();
        review.setBuyer(buyer);
        review.setItem(item);
        review.setProvider(provider);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsForItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        return reviewRepository.findByItemId(itemId);
    }

    public List<Review> getReviewsForProvider(Long providerId) {
        if (!providerRepository.existsById(providerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found");
        }
        return reviewRepository.findByProviderId(providerId);
    }
}

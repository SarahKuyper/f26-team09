package com.csc340.prestopaws.repository;

import com.csc340.prestopaws.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItemId(Long itemId);

    List<Review> findByProviderId(Long providerId);
}

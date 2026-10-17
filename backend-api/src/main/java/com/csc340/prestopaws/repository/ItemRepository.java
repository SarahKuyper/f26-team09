package com.csc340.prestopaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Project-specific:
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Optional: Find all items by a specific provider ID
    List<Item> findByProviderId(Long providerId);
}

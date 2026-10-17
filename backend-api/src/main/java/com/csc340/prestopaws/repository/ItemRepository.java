package com.csc340.prestopaws.repository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Optional: Find all items by a specific provider ID
    List<Item> findByProviderId(Long providerId);
}

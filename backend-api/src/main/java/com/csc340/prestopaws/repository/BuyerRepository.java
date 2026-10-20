package com.csc340.prestopaws.repository;

import com.csc340.prestopaws.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}

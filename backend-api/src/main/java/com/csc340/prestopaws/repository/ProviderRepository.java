package com.csc340.prestopaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Project-specific:
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}

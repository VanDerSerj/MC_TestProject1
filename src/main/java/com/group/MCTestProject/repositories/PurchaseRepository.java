package com.group.MCTestProject.repositories;

import com.group.MCTestProject.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    Optional<Purchase> findByName(String name);
}

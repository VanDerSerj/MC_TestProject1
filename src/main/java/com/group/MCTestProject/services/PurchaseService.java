package com.group.MCTestProject.services;

import com.group.MCTestProject.models.Purchase;
import com.group.MCTestProject.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Optional<Purchase> findByName(String name) {
        return purchaseRepository.findByName(name);
    }

    @Transactional
    public void register(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}

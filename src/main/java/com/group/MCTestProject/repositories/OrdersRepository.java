package com.group.MCTestProject.repositories;

import com.group.MCTestProject.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}

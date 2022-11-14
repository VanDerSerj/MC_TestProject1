package com.group.MCTestProject.services;

import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.repositories.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final PurchaseService purchaseService;

    public OrdersService(OrdersRepository ordersRepository,
                         PurchaseService purchaseService) {
        this.ordersRepository = ordersRepository;
        this.purchaseService = purchaseService;
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Transactional
    public void addOrders(Orders orders) {
        enrichOrders(orders);
        ordersRepository.save(orders);
    }

    public void enrichOrders(Orders orders) {
        // берем пукупку из БД (по имени) и вставлем объект из Hibernate persistence context'а
        orders.setPurchase(purchaseService.findByName(orders.getPurchase().getName()).get());
        orders.setOrdersDateTime(LocalDateTime.now());
    }

    public Orders findOne(int id) {
        Optional<Orders> foundPerson = ordersRepository.findById(id);
        return foundPerson.orElse(null);
    }


    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }
}

package com.group.MCTestProject.services;

import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.repositories.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;







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
        // мы должны сами найти сенсор из БД по имени и вставить объект из Hibernate persistence context'а
        orders.setPurchase(purchaseService.findByName(orders.getPurchase().getName()).get());

        orders.setOrdersDateTime(LocalDateTime.now());
    }



    //TODO: add delete method
}

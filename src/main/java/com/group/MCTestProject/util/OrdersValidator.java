package com.group.MCTestProject.util;

import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrdersValidator implements Validator {

    private final PurchaseService purchaseService;

    @Autowired
    public OrdersValidator(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Orders.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Orders orders = (Orders) o;

        if (orders.getPurchase() == null) {
            return;
        }

        if (purchaseService.findByName(orders.getPurchase().getName()).isEmpty())
            errors.rejectValue("purchase", "Doesn't exist");
    }
}

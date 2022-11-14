package com.group.MCTestProject.controllers;


import com.group.MCTestProject.dto.OrdersDTO;
import com.group.MCTestProject.dto.OrdersResponse;
import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.models.Purchase;
import com.group.MCTestProject.services.OrdersService;
import com.group.MCTestProject.services.PurchaseService;
import com.group.MCTestProject.util.OrdersValidator;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.group.MCTestProject.util.ErrorsUtil.returnErrorsToClient;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersValidator ordersValidator;

    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;



    @Autowired
    public OrdersController(OrdersService ordersService,
                            OrdersValidator ordersValidator,
                            PurchaseService purchaseService,
                            ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.ordersValidator = ordersValidator;
        this.purchaseService = purchaseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersService.findAll());
        return "orders/index";
    }


    @GetMapping("/weekly")
    public String lastWeekOrders(Model model) {
        model.addAttribute("orders", ordersService.findAll().stream().filter(s-> (s.getOrdersDateTime().compareTo(LocalDateTime.now().with(DayOfWeek.MONDAY))) <= 0).collect(Collectors.toList()));
        return "orders/weekly";
    }

    @GetMapping("/maxpurch")
    public String maxPurch(Model model) {
        model.addAttribute("orders", ordersService.findAll().stream().filter(s-> (s.getOrdersDateTime().compareTo(LocalDateTime.now().with(DayOfWeek.MONDAY))) == 0).collect(Collectors.toList()));
        return "orders/maxpurch";
    }

    @GetMapping("/newold")
    public String newOrders(@ModelAttribute("orders") Orders orders) {
        return "orders/new";
    }
    @GetMapping("/new")
    public String showItems(Model model,
                            @ModelAttribute("purchase") Purchase purchase,
                            @ModelAttribute("orders") Orders orders) {
        model.addAttribute("purchase_items", purchaseService.findAll());
        return "orders/items";
    }


    @PostMapping()
    public String create(@ModelAttribute("orders") @Valid Orders orders,
                         BindingResult bindingResult) {


        ordersValidator.validate(orders, bindingResult);

        if (bindingResult.hasErrors())
            return "orders/items";

        ordersService.addOrders(orders);
        //ordersService.save(orders);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("orders", ordersService.findOne(id));

        return "orders/show";
    }

}

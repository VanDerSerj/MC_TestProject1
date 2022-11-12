package com.group.MCTestProject.controllers;

import com.group.MCTestProject.dto.OrderDTO;
import com.group.MCTestProject.dto.OrdersDTO;
import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.services.OrdersService;
import com.group.MCTestProject.util.OrdersValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.group.MCTestProject.util.ErrorsUtil.returnErrorsToClient;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final OrdersValidator ordersValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public OrdersController(OrdersService ordersService,
                            OrdersValidator ordersValidator,
                            ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.ordersValidator = ordersValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersService.findAll());
        return "orders/index";
    }

    @GetMapping("/new")
    public String newOrders(@ModelAttribute("orders") Orders orders) {
        return "orders/new";
    }

/*    @PostMapping()
    public String create(@ModelAttribute("orders") @Valid Orders orders,
                         BindingResult bindingResult) {
        ordersValidator.validate(orders, bindingResult);

        if (bindingResult.hasErrors())
            return "orders/new";

        ordersService.addOrders(orders);
        return "redirect:/orders";
    } */
    @PostMapping()
    public String create(@ModelAttribute("orders") @Valid OrderDTO orderDTO,
                         BindingResult bindingResult) {
        Orders ordersToAdd = convertToOrders(orderDTO);

        ordersValidator.validate(ordersToAdd, bindingResult);
        if (bindingResult.hasErrors())
            return "orders/new";

        ordersService.addOrders(ordersToAdd);
        return "redirect:/orders";
    }




    private Orders convertToOrders(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Orders.class);
    }


}
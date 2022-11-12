package com.group.MCTestProject.controllers;

import com.group.MCTestProject.dto.OrdersDTO;
import com.group.MCTestProject.dto.OrdersResponse;
import com.group.MCTestProject.models.Orders;
import com.group.MCTestProject.services.OrdersService;
import com.group.MCTestProject.util.OrdersErrorResponse;
import com.group.MCTestProject.util.OrdersException;
import com.group.MCTestProject.util.OrdersValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.group.MCTestProject.util.ErrorsUtil.returnErrorsToClient;

@RestController
//@Controller
@RequestMapping("/od")
public class OrdersRestController {

    private final OrdersService ordersService;
    private final OrdersValidator ordersValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public OrdersRestController(OrdersService ordersService,
                                OrdersValidator ordersValidator,
                                ModelMapper modelMapper) {
        this.ordersService = ordersService;
        this.ordersValidator = ordersValidator;
        this.modelMapper = modelMapper;
    }





    //@ResponseBody
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid OrdersDTO ordersDTO,
                                          BindingResult bindingResult) {
        Orders ordersToAdd = convertToOrders(ordersDTO);

        ordersValidator.validate(ordersToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        ordersService.addOrders(ordersToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //@ResponseBody
    @GetMapping()
    public OrdersResponse getOrders() {
        return new OrdersResponse(ordersService.findAll().stream().map(this::convertToOrdersDTO)
                .collect(Collectors.toList()));
    }

    //@ResponseBody
    @GetMapping("/lastWeekOrders")
    public OrdersResponse getLastWeekOrders() {
        //TODO: Вывести список покупок за последнюю неделю
        return new OrdersResponse(ordersService.findAll().stream().map(this::convertToOrdersDTO)
                .collect(Collectors.toList()));
    }

    //@ResponseBody
    @GetMapping("/maxPurchased")
    public OrdersResponse getMaxPurchased() {
        //TODO: Вывести самый покупаемый товар за последний месяц
        return new OrdersResponse(ordersService.findAll().stream().map(this::convertToOrdersDTO)
                .collect(Collectors.toList()));
    }

    //@ResponseBody
    @GetMapping("/maxHalfYearCustomer")
    public OrdersResponse getMaxHalfYearCustomer() {
        //TODO: Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
        return new OrdersResponse(ordersService.findAll().stream().map(this::convertToOrdersDTO)
                .collect(Collectors.toList()));
    }

    //@ResponseBody
    @GetMapping("/maxEighteenPurchase")
    public OrdersResponse getMaxEighteenPurchase() {
        //TODO: Что чаще всего покупают люди в возрасте 18 лет
        return new OrdersResponse(ordersService.findAll().stream().map(this::convertToOrdersDTO)
                .collect(Collectors.toList()));
    }

    private Orders convertToOrders(OrdersDTO ordersDTO) {
        return modelMapper.map(ordersDTO, Orders.class);
    }

    private OrdersDTO convertToOrdersDTO(Orders orders) {
        return modelMapper.map(orders, OrdersDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<OrdersErrorResponse> handleException(OrdersException e) {
        OrdersErrorResponse response = new OrdersErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}

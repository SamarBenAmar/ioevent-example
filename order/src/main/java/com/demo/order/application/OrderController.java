package com.demo.order.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.application.dto.CreateOrderDto;
import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.ports.api.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping
    public OrderCreated creatOrder(@RequestBody CreateOrderDto dto) throws Exception{
        return orderService.createOrder(new CustomerId(dto.customerId()), dto.name(), new Money(dto.price()), dto.discount());
    }

}

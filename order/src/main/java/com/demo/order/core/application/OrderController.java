package com.demo.order.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.core.application.dto.CreateOrderDto;
import com.demo.order.core.application.ports.api.OrderEventHandler;
import com.demo.order.core.application.ports.api.OrderService;
import com.demo.order.core.domain.events.OrderCreated;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderEventHandler orderEventHandler;

    @PostMapping
    public OrderCreated creatOrder(@RequestBody CreateOrderDto dto) throws Exception{
        return orderEventHandler.createOrder(new CustomerId(dto.customerId()), dto.name(), new Money(dto.price()), dto.discount());
    }

}

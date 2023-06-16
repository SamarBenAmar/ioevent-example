package com.demo.order.infrastructure.adapters.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.order.core.application.ports.api.OrderWorkflowService;
import com.demo.order.core.application.ports.api.OrderResource;
import com.demo.order.core.application.ports.api.OrderService;
import com.demo.order.core.domain.events.OrderCreated;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.infrastructure.adapters.rest.controller.dto.CreateOrderDto;

@RestController
@RequestMapping("/orders")
public class OrderRestController implements OrderResource{
    
    @Autowired
    OrderWorkflowService orderWorkflowService;

    @Override
    @PostMapping
    public OrderCreated createOrder(@RequestBody CreateOrderDto dto) throws Exception{
        return orderWorkflowService.createOrder(new CustomerId(dto.customerId()), dto.name(), new Money(dto.price()), dto.discount());
    }

}

package com.demo.order.core.application.ports.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.order.core.domain.events.OrderCreated;
import com.demo.order.infrastructure.adapters.rest.controller.dto.CreateOrderDto;

public interface OrderResource {
    @PostMapping
    public OrderCreated createOrder(@RequestBody CreateOrderDto dto) throws Exception;
}

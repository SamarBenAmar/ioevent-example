package com.demo.order.infrastructure.adapters.rest.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderDto(UUID customerId, String name, BigDecimal price, float discount) {
    
}

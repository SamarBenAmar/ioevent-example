package com.demo.order.core.application.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderDto(UUID customerId, String name, BigDecimal price, float discount) {
    
}

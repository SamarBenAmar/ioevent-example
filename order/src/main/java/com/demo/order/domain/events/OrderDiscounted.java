package com.demo.order.domain.events;

import java.math.BigDecimal;
import java.util.UUID;

import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.OrderId;

public record OrderDiscounted(UUID eventId, OrderId orderId, Money price) {

    public OrderDiscounted {}

    public OrderDiscounted(OrderId orderId, Money price){
        this(UUID.randomUUID(), orderId, price);
    }
   
    
    public String aggregateId() {
        return orderId.toString();
    }

    
    public String aggregateType() {
        return "Order";
    }

    
    public String eventType() {
        return "OrderDiscounted";
    }
    
}

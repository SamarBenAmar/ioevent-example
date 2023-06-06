package com.demo.discount.domain.events;

import java.util.UUID;

import com.demo.discount.domain.model.Money;
import com.demo.discount.domain.model.OrderId;

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

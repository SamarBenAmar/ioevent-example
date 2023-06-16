package com.demo.order.domain.events;

import java.util.UUID;

import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.OrderId;

public record OrderCreated(UUID eventId, OrderId orderId, Money price, float discount) {

    public OrderCreated(OrderId orderId, Money price, float discount){
        this(UUID.randomUUID(), orderId, price, discount);
    }
    
    public String aggregateId() {
        return orderId.value().toString();
    }
    
    public String aggregateType() {
        return "Order";
    }

    public String eventType() {
        return "OrderCreated";
    }
    
}

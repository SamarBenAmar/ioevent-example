package com.demo.order.domain.events;

import java.util.UUID;

import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.OrderId;

public record OrderClosed(UUID eventId, OrderId orderId, Money price, boolean closed) {

    public OrderClosed(OrderId orderId, Money price, boolean closed){
        this(UUID.randomUUID(), orderId, price, closed);
    }
    
    public String aggregateId() {
        return orderId.value().toString();
    }
    
    public String aggregateType() {
        return "Order";
    }

    public String eventType() {
        return "OrderClosed";
    }
}

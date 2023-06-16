package com.demo.order.core.domain.events;

import java.util.UUID;

import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.OrderId;

public record OrderConfirmed(UUID eventId, OrderId orderId, CustomerId customerId, String name, Money price) {

    public OrderConfirmed(OrderId orderId, CustomerId customerId, String name, Money price){
        this(UUID.randomUUID(), orderId, customerId, name, price);
    }
    
    public String aggregateId() {
        return orderId.value().toString();
    }
    
    public String aggregateType() {
        return "Order";
    }

    public String eventType() {
        return "OrderConfirmed";
    }
    
    
}

package com.demo.order.core.domain.model;

import java.util.UUID;

public record OrderId(UUID value) {

    public OrderId {}

    public String stringValue() {
        return value.toString();
    }
    public OrderId(){
        this(UUID.randomUUID());
    }
    
}

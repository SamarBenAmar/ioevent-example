package com.demo.order.domain.model;

import java.util.UUID;

public record CustomerId(UUID value) {

    public String stringValue() {
        return value.toString();
    }
    public CustomerId(){
        this(UUID.randomUUID());
    }
    
}

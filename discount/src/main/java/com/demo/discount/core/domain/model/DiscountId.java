package com.demo.discount.core.domain.model;

import java.util.UUID;

public record DiscountId(UUID value) {

    public String stringValue() {
        return value.toString();
    }
    public DiscountId(){
        this(UUID.randomUUID());
    }
    
}

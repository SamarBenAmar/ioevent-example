package com.demo.discount.domain.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;

public record OrderId(UUID value) {

    public OrderId {}

    public String stringValue() {
        return value.toString();
    }
    public OrderId(){
        this(UUID.randomUUID());
    }
    
}
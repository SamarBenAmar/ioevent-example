package com.demo.discount.domain.ports.api;

import java.util.UUID;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.events.OrderDiscounted;
import com.ioevent.starter.annotations.IOResponse;

public interface DiscountService {

    IOResponse<UUID> checkDiscount(OrderCreated order) throws Exception;
    OrderDiscounted  makeDiscount(UUID orderId) throws Exception;
    
}

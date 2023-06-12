package com.demo.discount.domain.ports.api;

import java.util.UUID;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.model.Order;

public interface DiscountService {

    Order checkDiscount(OrderCreated order) throws Exception;
    Order  makeDiscount(UUID orderId) throws Exception;
    
}

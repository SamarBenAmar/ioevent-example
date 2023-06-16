package com.demo.discount.core.application.ports.api;

import java.util.UUID;

import com.demo.discount.core.domain.events.OrderCreated;
import com.demo.discount.core.domain.model.Order;

public interface DiscountService {

    Order checkDiscount(OrderCreated order) throws Exception;
    Order  makeDiscount(UUID orderId) throws Exception;
    
}

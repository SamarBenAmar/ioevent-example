package com.demo.discount.domain.eventsHandlers;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.events.OrderDiscounted;
import com.demo.discount.domain.model.Order;
import com.ioevent.starter.annotations.IOResponse;

public interface DiscountEventHandler {
    IOResponse<Order> checkDiscount(OrderCreated order) throws Exception;
    OrderDiscounted  makeDiscount(Order order) throws Exception;
}

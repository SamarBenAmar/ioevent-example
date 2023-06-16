package com.demo.discount.core.application.ports.api;

import com.demo.discount.core.domain.events.OrderCreated;
import com.demo.discount.core.domain.events.OrderDiscounted;
import com.demo.discount.core.domain.model.Order;
import com.ioevent.starter.annotations.IOResponse;

public interface OrderWorkflowService {
    IOResponse<Order> checkDiscount(OrderCreated order) throws Exception;
    OrderDiscounted  makeDiscount(Order order) throws Exception;
}

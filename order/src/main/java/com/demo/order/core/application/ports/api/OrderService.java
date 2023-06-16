package com.demo.order.core.application.ports.api;

import java.util.UUID;

import com.demo.order.core.domain.events.OrderConfirmed;
import com.demo.order.core.domain.events.OrderDiscounted;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;

public interface OrderService {

    Order createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception;
    Order updateOrder(OrderDiscounted orderDiscounted);
    Order confirmOrder(Order order);
    Order shipOrder(OrderConfirmed orderConfirmed);
    Order prepareInvoice(UUID orderId);
    Order prepareOrder(UUID orderId);
    Order closeOrder(UUID orderId);
}

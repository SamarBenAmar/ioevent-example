package com.demo.order.domain.ports.api;

import java.util.UUID;

import com.demo.order.domain.events.OrderClosed;
import com.demo.order.domain.events.OrderConfirmed;
import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.events.OrderDiscounted;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;

public interface OrderService {

    OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception;
    UUID updateOrder(OrderDiscounted orderDiscounted);
    OrderConfirmed confirmOrder(UUID orderId);
    UUID shipOrder(OrderConfirmed orderConfirmed);
    UUID prepareInvoice(UUID orderId);
    UUID prepareOrder(UUID orderId);
    OrderClosed closeOrder(UUID orderId);
}

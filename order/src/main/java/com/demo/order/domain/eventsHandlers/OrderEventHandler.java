package com.demo.order.domain.eventsHandlers;

import com.demo.order.domain.events.OrderClosed;
import com.demo.order.domain.events.OrderConfirmed;
import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.events.OrderDiscounted;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.Order;

public interface OrderEventHandler {
    OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception;
    Order updateOrder(OrderDiscounted orderDiscounted);
    OrderConfirmed confirmOrder(Order order);
    Order shipOrder(OrderConfirmed orderConfirmed);
    Order prepareInvoice(Order order);
    Order prepareOrder(Order order);
    OrderClosed closeOrder(Order order);
}

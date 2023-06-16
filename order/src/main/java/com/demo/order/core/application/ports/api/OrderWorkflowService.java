package com.demo.order.core.application.ports.api;

import com.demo.order.core.domain.events.OrderClosed;
import com.demo.order.core.domain.events.OrderConfirmed;
import com.demo.order.core.domain.events.OrderCreated;
import com.demo.order.core.domain.events.OrderDiscounted;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.Order;

public interface OrderWorkflowService {
    OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception;
    Order updateOrder(OrderDiscounted orderDiscounted);
    OrderConfirmed confirmOrder(Order order);
    Order shipOrder(OrderConfirmed orderConfirmed);
    Order prepareInvoice(Order order);
    Order prepareOrder(Order order);
    OrderClosed closeOrder(Order order);
}

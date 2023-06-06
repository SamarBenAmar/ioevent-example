package com.demo.order.domain.ports.api;

import java.math.BigDecimal;
import java.util.UUID;

import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.events.OrderDiscounted;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;

public interface OrderService {

    OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount, boolean valid) throws Exception;
    UUID updateOrder(OrderDiscounted orderDiscounted);
    OrderId confirmOrder(UUID orderId);
}

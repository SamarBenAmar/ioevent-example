package com.demo.order.core.application.ports.spi;

import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;

public interface OrderRepository {
    
    Order save(Order order);
    Order lookup(OrderId orderId);
}

package com.demo.order.domain.ports.spi;

import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;

public interface OrderRepository {
    
    void save(Order order);
    Order lookup(OrderId orderId);
}

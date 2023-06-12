package com.demo.discount.domain.ports.spi;

import com.demo.discount.domain.model.DiscountId;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;

public interface DiscountRepo {
    Order save(Order order);
    Order lookUp(DiscountId orderId);
    Order findByOrderId(OrderId orderId);
}

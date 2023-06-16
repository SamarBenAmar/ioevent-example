package com.demo.discount.core.application.ports.spi;

import com.demo.discount.core.domain.model.DiscountId;
import com.demo.discount.core.domain.model.Order;
import com.demo.discount.core.domain.model.OrderId;

public interface DiscountRepo {
    Order save(Order order);
    Order lookUp(DiscountId orderId);
    Order findByOrderId(OrderId orderId);
}

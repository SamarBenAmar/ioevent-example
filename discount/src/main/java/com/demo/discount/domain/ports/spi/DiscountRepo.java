package com.demo.discount.domain.ports.spi;

import com.demo.discount.domain.model.DiscountId;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;

public interface DiscountRepo {
    void save(Order order);
    Order lookUp(DiscountId orderId);
}

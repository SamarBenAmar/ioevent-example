package com.demo.discount.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.discount.domain.model.DiscountId;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;

@Repository
public interface DiscountMongoRepo extends MongoRepository<Order, DiscountId>{
    Order findByOrderId(OrderId orderId);
}

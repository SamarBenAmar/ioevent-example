package com.demo.discount.infrastructure.adapters.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.discount.core.application.ports.spi.DiscountRepo;
import com.demo.discount.core.domain.model.DiscountId;
import com.demo.discount.core.domain.model.Order;
import com.demo.discount.core.domain.model.OrderId;

@Service
public class DiscountRepoAdapter implements DiscountRepo{

    @Autowired
    DiscountMongoRepo discountMongoRepo;

    @Override
    public Order save(Order order) {
        return discountMongoRepo.save(order);
    }

    @Override
    public Order lookUp(DiscountId orderId) {
        if (discountMongoRepo.findById(orderId).isPresent()) {
            return discountMongoRepo.findById(orderId).get();
        } else {
            return (Order) Optional.empty().get();
        }
    }

    @Override
    public Order findByOrderId(OrderId orderId) {
        return discountMongoRepo.findByOrderId(orderId);
    }
    
}

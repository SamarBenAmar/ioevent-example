package com.demo.discount.infrastructure;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.discount.domain.model.DiscountId;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;
import com.demo.discount.domain.ports.spi.DiscountRepo;

@Service
public class DiscountRepoAdapter implements DiscountRepo{

    @Autowired
    DiscountMongoRepo discountMongoRepo;

    @Override
    public void save(Order order) {
        discountMongoRepo.save(order);
    }

    @Override
    public Order lookUp(DiscountId orderId) {
        if (discountMongoRepo.findById(orderId).isPresent()) {
            return discountMongoRepo.findById(orderId).get();
        } else {
            return (Order) Optional.empty().get();
        }
    }
    
}

package com.demo.discount.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.model.Money;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;
import com.demo.discount.domain.ports.api.DiscountService;
import com.demo.discount.domain.ports.spi.DiscountRepo;

@Service
public class DiscountServiceImpl implements DiscountService  {

    @Autowired
    DiscountRepo discountRepo;

    public Order checkDiscount(OrderCreated orderCreated) throws Exception {
        Order order = Order.of(orderCreated.orderId(), orderCreated.price(), orderCreated.discount());
        if(order.hasDiscount()){
            return discountRepo.save(order);
        } else {
           return order;
        }
    }

    @Override
    public Order makeDiscount(UUID value) throws Exception{
        Order order = discountRepo.findByOrderId(new OrderId(value));
        order.setPrice(new Money(order.discount(order.getPrice(), order.getDiscount())));
        return discountRepo.save(order);
    } 
    
}

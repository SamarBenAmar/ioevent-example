package com.demo.discount.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.events.OrderDiscounted;
import com.demo.discount.domain.model.Money;
import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.model.OrderId;
import com.demo.discount.domain.ports.api.DiscountService;
import com.demo.discount.domain.ports.spi.DiscountRepo;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.IOResponse;
import com.ioevent.starter.annotations.InputEvent;
import com.ioevent.starter.annotations.OutputEvent;
import com.ioevent.starter.annotations.GatewayOutputEvent;


@Service
@IOFlow(name = "order_workflow")
public class DiscountServiceImpl implements DiscountService  {

    @Autowired
    DiscountRepo discountRepo;

    @Override
    @IOEvent(key = "check discount", topic = "order", //
    input = @InputEvent(key = "order created"), //
    gatewayOutput = @GatewayOutputEvent(exclusive = true, output = { //
            @OutputEvent(key = "discount offered"), //
            @OutputEvent(key = "order valid")//
    }))
    public IOResponse<UUID> checkDiscount(OrderCreated orderCreated) throws Exception {
        Order order = Order.of(orderCreated.orderId(), orderCreated.price(), orderCreated.discount());
        if(order.hasDiscount()){
            discountRepo.save(order);
            return new IOResponse<>("discount offered", order.id().value());
        } else {
            return new IOResponse<>("order valid", orderCreated.orderId().value());
        }
    }

    @Override
    @IOEvent(key = "make discount", topic = "order", input = @InputEvent(key = "discount offered"), output = @OutputEvent(key = "order valid"))
    public OrderDiscounted makeDiscount(UUID value) throws Exception{
        Order order = discountRepo.lookUp(new OrderId(value));
        order.setPrice(new Money(order.discount(order.getPrice(), order.getDiscount())));
        discountRepo.save(order);
        return new OrderDiscounted(order.getOrderId(), order.getPrice());
    } 
    
}

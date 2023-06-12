package com.demo.discount.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.discount.domain.events.OrderCreated;
import com.demo.discount.domain.events.OrderDiscounted;
import com.demo.discount.domain.eventsHandlers.DiscountEventHandler;

import com.demo.discount.domain.model.Order;
import com.demo.discount.domain.ports.api.DiscountService;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.IOResponse;
import com.ioevent.starter.annotations.InputEvent;
import com.ioevent.starter.annotations.OutputEvent;
import com.ioevent.starter.annotations.GatewayOutputEvent;


@Service
@IOFlow(name = "order_workflow")
public class DiscountEventHandlerAdapter implements DiscountEventHandler  {

    @Autowired
    DiscountService discountService;

    @Override
    @IOEvent(key = "check discount", topic = "order", //
    input = @InputEvent(key = "order created"), //
    gatewayOutput = @GatewayOutputEvent(exclusive = true, output = { //
            @OutputEvent(key = "discount offered"), //
            @OutputEvent(key = "order valid")//
    }))
    public IOResponse<Order> checkDiscount(OrderCreated orderCreated) throws Exception {
        Order order = discountService.checkDiscount(orderCreated);
        if(order.hasDiscount()){
            
            return new IOResponse<>("discount offered", order);
        } else {
            return new IOResponse<>("order valid", order);
        }
    }

    @Override
    @IOEvent(key = "make discount", topic = "order", input = @InputEvent(key = "discount offered"), output = @OutputEvent(key = "order discounted"))
    public OrderDiscounted makeDiscount(Order order) throws Exception{
        Order orderDiscounted = discountService.makeDiscount(order.getOrderId().value());
        return new OrderDiscounted(orderDiscounted.getOrderId(), orderDiscounted.getPrice());
    } 
    
}


package com.demo.order.domain.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.events.OrderDiscounted;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;
import com.demo.order.domain.ports.api.OrderService;
import com.demo.order.domain.ports.spi.OrderRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.IOPayload;
import com.ioevent.starter.annotations.OutputEvent;
import com.ioevent.starter.annotations.InputEvent;

@Service
@IOFlow(name = "order_workflow")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;
 
    @Override
    @IOEvent(key = "create order", topic = "order", output = @OutputEvent(key = "order created"))
    public OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount, boolean valid) throws Exception{
        Order order = Order.of(customerId, name, price, discount, valid);
        orderRepo.save(order);
        return new OrderCreated(order.id(), order.getPrice(), order.getDiscount());
    }

    @Override
    @IOEvent(key = "update order", topic = "order", input = @InputEvent(key = "order discounted"), output = @OutputEvent(key = "order valid"))
    public UUID updateOrder(@JsonDeserialize OrderDiscounted orderDiscounted){
       
        Order order = orderRepo.lookup(new OrderId(orderDiscounted.orderId().value()));
        order.setPrice(new Money(orderDiscounted.price().value()));
        orderRepo.save(order);
        return order.id().value();
    }

    @Override
    @IOEvent(key = "confirm order", topic = "order", input = @InputEvent(key = "order valid"), output = @OutputEvent(key="order confirmed"))
    public OrderId confirmOrder(UUID value) {        
        Order order = orderRepo.lookup(new OrderId(value));
        order.setValid(true);
        orderRepo.save(order);
        return order.id();
    }



    /* public List<Order> findAll(){
        List<Order> orders = orderRepo.findAll();
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            Order newOrder = new Order(order.getId(), order.getName(), order.getPrice() + 100, false, null);
            result.add(newOrder);
        }
        return result;
    } */

   
}

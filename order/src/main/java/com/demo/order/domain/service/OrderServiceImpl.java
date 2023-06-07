package com.demo.order.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.domain.events.OrderClosed;
import com.demo.order.domain.events.OrderConfirmed;
import com.demo.order.domain.events.OrderCreated;
import com.demo.order.domain.events.OrderDiscounted;
import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;
import com.demo.order.domain.ports.api.OrderService;
import com.demo.order.domain.ports.spi.OrderRepository;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.OutputEvent;
import com.ioevent.starter.annotations.InputEvent;
import com.ioevent.starter.annotations.GatewayOutputEvent;
import com.ioevent.starter.annotations.GatewayInputEvent;

@Service
@IOFlow(name = "order_workflow")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;
 
    @Override
    @IOEvent(key = "create order", topic = "order", output = @OutputEvent(key = "order created"))
    public OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception{
        Order order = Order.of(customerId, name, price, discount, false, false, false, false, false );
        orderRepo.save(order);
        return new OrderCreated(order.id(), order.getPrice(), order.getDiscount());
    }

    @Override
    @IOEvent(key = "update order", topic = "order", input = @InputEvent(key = "order discounted"), output = @OutputEvent(key = "order valid"))
    public UUID updateOrder(OrderDiscounted orderDiscounted){
       
        Order order = orderRepo.lookup(orderDiscounted.orderId());
        order.setPrice(new Money(orderDiscounted.price().value()));
        orderRepo.save(order);
        return order.id().value();
    }

    @Override
    @IOEvent(key = "confirm order", topic = "order", input = @InputEvent(key = "order valid"), output = @OutputEvent(key="order confirmed"))
    public OrderConfirmed confirmOrder(UUID value) {        
        Order order = orderRepo.lookup(new OrderId(value));
        order.setValid(true);
        orderRepo.save(order);
        return new OrderConfirmed(order.id(), order.getCustomerId(), order.getName(), order.getPrice());
    }

    @Override
    @IOEvent(key = "ship order", topic = "order", //
    input = @InputEvent(key = "order confirmed"), //
    gatewayOutput = @GatewayOutputEvent(parallel = true, output = { //
            @OutputEvent(key = "invoice to prepare"), //
            @OutputEvent(key = "order to prepare")//
    }))
    public UUID shipOrder(OrderConfirmed orderConfirmed) {
        Order order = orderRepo.lookup(orderConfirmed.orderId());
        order.setShipped(true);
        orderRepo.save(order);
        return order.id().value();
    }

    @Override
    @IOEvent(key = "prepare invoice", topic = "order", input = @InputEvent(key = "invoice to prepare"), output = @OutputEvent(key = "invoice prepared"))
    public UUID prepareInvoice(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setHasInvoice(true);
        orderRepo.save(order);
        return order.id().value();
    }

    @Override
    @IOEvent(key = "prepare order", topic = "order", input = @InputEvent(key = "order to prepare"), output = @OutputEvent(key = "order prepared"))
    public UUID prepareOrder(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setPrepared(true);
        orderRepo.save(order);
        return order.id().value();
    }

    @Override
    @IOEvent(key = "close order", topic = "order", //
    gatewayInput = @GatewayInputEvent(parallel = true, input = { //
            @InputEvent(key = "invoice prepared"), //
            @InputEvent(key = "order prepared")//
    }), output = @OutputEvent(key = "order closed"))
    public OrderClosed closeOrder(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setClosed(true);
        orderRepo.save(order);
        return new OrderClosed(order.id(), order.getPrice(), order.isClosed());
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

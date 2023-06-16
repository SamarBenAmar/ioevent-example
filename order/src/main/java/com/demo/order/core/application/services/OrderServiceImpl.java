package com.demo.order.core.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.core.application.ports.api.OrderService;
import com.demo.order.core.application.ports.spi.OrderRepository;
import com.demo.order.core.domain.events.OrderConfirmed;
import com.demo.order.core.domain.events.OrderDiscounted;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Override
    public Order createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception {
        Order order = Order.of(customerId, name, price, discount, false, false, false, false, false);
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(OrderDiscounted orderDiscounted) {
        Order order = orderRepo.lookup(orderDiscounted.orderId());
        order.setPrice(new Money(orderDiscounted.price().value()));
        return orderRepo.save(order);
    }

    @Override
    public Order confirmOrder(Order order) {
        Order orderToUpdate = orderRepo.lookup(order.id());
        orderToUpdate.setValid(true);
        return orderRepo.save(orderToUpdate);
    }

    @Override
    public Order shipOrder(OrderConfirmed orderConfirmed) {
        Order order = orderRepo.lookup(orderConfirmed.orderId());
        order.setShipped(true);
        return orderRepo.save(order);
    }

    @Override
    public Order prepareInvoice(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setHasInvoice(true);
        return orderRepo.save(order);
    }

    @Override
    public Order prepareOrder(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setPrepared(true);
        return orderRepo.save(order);
    }

    @Override
    public Order closeOrder(UUID orderId) {
        Order order = orderRepo.lookup(new OrderId(orderId));
        order.setClosed(true);
        return orderRepo.save(order);
    }

}

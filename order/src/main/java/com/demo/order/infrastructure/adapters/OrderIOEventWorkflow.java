package com.demo.order.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.core.application.ports.api.OrderWorkflowService;
import com.demo.order.core.application.ports.api.OrderService;
import com.demo.order.core.domain.events.OrderClosed;
import com.demo.order.core.domain.events.OrderConfirmed;
import com.demo.order.core.domain.events.OrderCreated;
import com.demo.order.core.domain.events.OrderDiscounted;
import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.Order;
import com.ioevent.starter.annotations.IOEvent;
import com.ioevent.starter.annotations.IOFlow;
import com.ioevent.starter.annotations.OutputEvent;
import com.ioevent.starter.annotations.InputEvent;
import com.ioevent.starter.annotations.GatewayOutputEvent;
import com.ioevent.starter.annotations.GatewayInputEvent;

@Service
@IOFlow(name = "order_workflow")
public class OrderIOEventWorkflow implements OrderWorkflowService {

    @Autowired
    OrderService orderService;

    @Override
    @IOEvent(key = "create order", topic = "order", output = @OutputEvent(key = "order created"))
    public OrderCreated createOrder(CustomerId customerId, String name, Money price, float discount) throws Exception {
        Order order = orderService.createOrder(customerId, name, price, discount);
        return new OrderCreated(order.id(), order.getPrice(), order.getDiscount());
    }

    @Override
    @IOEvent(key = "update order", topic = "order", input = @InputEvent(key = "order discounted"), output = @OutputEvent(key = "order valid"))
    public Order updateOrder(OrderDiscounted orderDiscounted) {
        return orderService.updateOrder(orderDiscounted);
    }

    @Override
    @IOEvent(key = "confirm order", topic = "order", input = @InputEvent(key = "order valid"), output = @OutputEvent(key = "order confirmed"))
    public OrderConfirmed confirmOrder(Order order) {
        Order confirmedOrder = orderService.confirmOrder(order);
        return new OrderConfirmed(confirmedOrder.id(), confirmedOrder.getCustomerId(), confirmedOrder.getName(), confirmedOrder.getPrice());
    }

    @Override
    @IOEvent(key = "ship order", topic = "order", //
            input = @InputEvent(key = "order confirmed"), //
            gatewayOutput = @GatewayOutputEvent(parallel = true, output = { //
                    @OutputEvent(key = "invoice to prepare"), //
                    @OutputEvent(key = "order to prepare")//
            }))
    public Order shipOrder(OrderConfirmed orderConfirmed) {
        return orderService.shipOrder(orderConfirmed);
        
    }

    @Override
    @IOEvent(key = "prepare invoice", topic = "order", input = @InputEvent(key = "invoice to prepare"), output = @OutputEvent(key = "invoice prepared"))
    public Order prepareInvoice(Order order) {
        return orderService.prepareInvoice(order.id().value());
    }

    @Override
    @IOEvent(key = "prepare order", topic = "order", input = @InputEvent(key = "order to prepare"), output = @OutputEvent(key = "order prepared"))
    public Order prepareOrder(Order order) {
        return orderService.prepareOrder(order.id().value());
    }

    @Override
    @IOEvent(key = "close order", topic = "order", //
            gatewayInput = @GatewayInputEvent(parallel = false, input = { //
                    @InputEvent(key = "invoice prepared"), //
                    @InputEvent(key = "order prepared")//
            }), output = @OutputEvent(key = "order closed"))
    public OrderClosed closeOrder(Order order) {
        Order closedOrder = orderService.closeOrder(order.id().value());
        return new OrderClosed(closedOrder.id(), closedOrder.getPrice(), closedOrder.isClosed());
    }

}

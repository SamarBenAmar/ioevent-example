package com.demo.order.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.order.core.domain.model.CustomerId;
import com.demo.order.core.domain.model.Money;
import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orders")
public class OrderEntity {
    @Id
    private OrderId orderId;
    private CustomerId customerId;
    private String name;
    private Money price;
    private float discount;
    private boolean valid;
    private boolean shipped;
    private boolean prepared;
    private boolean hasInvoice;
    private boolean closed;

    public OrderEntity(Order order){
        orderId = order.id();
        customerId = order.getCustomerId();
        name = order.getName();
        price = order.getPrice();
        discount = order.getDiscount();
        valid = order.isValid();
        shipped = order.isShipped();
        prepared = order.isPrepared();
        hasInvoice = order.isHasInvoice();
        closed = order.isClosed();
    }
}

package com.demo.order.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.order.domain.model.CustomerId;
import com.demo.order.domain.model.Money;
import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;

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
    //private boolean valid = false;
    //private String description;

    public OrderEntity(Order order){
        orderId = order.id();
        customerId = order.getCustomerId();
        name = order.getName();
        price = order.getPrice();
        discount = order.getDiscount();
    }
}

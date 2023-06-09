package com.demo.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  
    private OrderId orderId;
    private CustomerId customerId;
    private String name;
    private Money price;
    private float discount;
    private boolean valid = false;
    private boolean shipped = false;
    private boolean prepared = false;
    private boolean hasInvoice = false;
    private boolean closed = false;

    public static Order of(CustomerId customerId, String name, Money price, float discount, 
                            boolean valid, boolean shipped, boolean prepared, boolean hasInvoice, boolean closed) throws Exception{
        if (customerId == null){
            throw new Exception("cannot create order without a customer");
        } else {
            return new Order(new OrderId(), customerId, name, price, discount, valid, shipped, prepared, hasInvoice, closed);
        }
    }

    public OrderId id(){
        return orderId;
    }

}

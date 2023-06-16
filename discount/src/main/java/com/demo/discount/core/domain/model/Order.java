package com.demo.discount.core.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("discount")
public class Order {
    @Id
    @JsonIgnore
    private DiscountId discountId;
    private OrderId orderId;
    private Money price;
    private float discount;

    public static Order of(OrderId orderId, Money price, float discount) throws Exception{
            return new Order(new DiscountId(UUID.randomUUID()), orderId, price, discount);
    }

    public DiscountId id(){
        return discountId;
    }

    public OrderId oId(){
        return orderId;
    }

    public boolean hasDiscount(){
        return discount != 0;
    }

    public BigDecimal discount(Money price, float discount){
        
        return price.value().subtract(price.value().multiply((BigDecimal.valueOf(discount))));
    }

}

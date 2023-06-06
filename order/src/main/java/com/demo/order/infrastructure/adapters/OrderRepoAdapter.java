package com.demo.order.infrastructure.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.domain.model.Order;
import com.demo.order.domain.model.OrderId;
import com.demo.order.domain.ports.spi.OrderRepository;
import com.demo.order.infrastructure.entities.OrderEntity;
import com.demo.order.infrastructure.mappers.OrderMapper;
import com.demo.order.infrastructure.repository.OrderMongoRepository;

@Service
public class OrderRepoAdapter implements OrderRepository{

    @Autowired
    OrderMongoRepository orderMongoRepository;

    @Override
    public void save(Order order) {
        orderMongoRepository.save(new OrderEntity(order));
    }

    @Override
    public Order lookup(OrderId orderId) {
        if (orderMongoRepository.findById(orderId).isPresent()) {
            return OrderMapper.instance.toDomain(orderMongoRepository.findById(orderId).get());
        } else {
            return (Order) Optional.empty().get();
        }
    }
    
}

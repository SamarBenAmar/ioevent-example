package com.demo.order.infrastructure.adapters.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order.core.application.ports.spi.OrderRepository;
import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;
import com.demo.order.infrastructure.adapters.db.entities.OrderEntity;
import com.demo.order.infrastructure.adapters.db.mappers.OrderMapper;
import com.demo.order.infrastructure.adapters.db.repository.OrderMongoRepository;

@Service
public class OrderMongoAdapter implements OrderRepository{

    @Autowired
    OrderMongoRepository orderMongoRepository;

    @Override
    public Order save(Order order) {
        return OrderMapper.instance.toDomain(orderMongoRepository.save(new OrderEntity(order)));
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

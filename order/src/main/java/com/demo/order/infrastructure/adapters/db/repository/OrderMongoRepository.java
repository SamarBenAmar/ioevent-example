package com.demo.order.infrastructure.adapters.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.order.core.domain.model.Order;
import com.demo.order.core.domain.model.OrderId;
import com.demo.order.infrastructure.adapters.db.entities.OrderEntity;

@Repository
public interface OrderMongoRepository extends MongoRepository<OrderEntity, OrderId>{
    
}

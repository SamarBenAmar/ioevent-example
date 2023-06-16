package com.demo.order.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.order.core.domain.model.Order;
import com.demo.order.infrastructure.entities.OrderEntity;

@Mapper
public interface OrderMapper {

    OrderMapper instance = Mappers.getMapper(OrderMapper.class);
    
    Order toDomain(OrderEntity orderEntity);

    OrderEntity fromDoamin(Order order);
    
}

package com.demo.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.order.domain.model.Order;
import com.demo.order.domain.service.OrderServiceImpl;
import com.demo.order.infrastructure.adapters.OrderRepoAdapter;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl orderService = new OrderServiceImpl();

    @Mock
    OrderRepoAdapter orderRepo;

    @BeforeEach
	public void init() {		
        MockitoAnnotations.initMocks(this);
	}
    
  /*   @Test
    public void testFindAll(){
        Order order1 = new Order("1", "order1", 10, false, null);
        Order order2 = new Order("2", "order2", 20, false, null);
        Order order3 = new Order("3", "order3", 30, false, null);
        List<Order> orders = new ArrayList<>();
        orders.add(order3);
        orders.add(order2);
        orders.add(order1);
        Order order4 = new Order("1", "order1", 110, false, null);
        Order order5 = new Order("2", "order2", 120, false, null);
        Order order6 = new Order("3", "order3", 130, false, null);
        List<Order> ordersExp = new ArrayList<>();
        ordersExp.add(order4);
        ordersExp.add(order5);
        ordersExp.add(order6);
        when(orderRepo.findAll()).thenReturn(orders); 
        System.out.println(orderService.findAll());
        assertEquals(ordersExp, orderService.findAll());
    } */
}

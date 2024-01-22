package com.e_commerce.Service;

import com.e_commerce.Entity.Order;
import com.e_commerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface OrderService {
    Order createOrder(Long userId);
    List<Order> getAllOrders(Order order);


}

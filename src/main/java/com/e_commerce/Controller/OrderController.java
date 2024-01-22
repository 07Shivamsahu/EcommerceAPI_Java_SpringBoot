package com.e_commerce.Controller;

import com.e_commerce.Entity.Order;
import com.e_commerce.Service.OrderService;
import com.e_commerce.Service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("create/{userid}")
    public Order createOrder(@PathVariable Long userid){
        return orderService.createOrder(userid);
    }
    @GetMapping
    public List<Order> getAllOrders(@RequestBody Order order){
        return orderService.getAllOrders(order);
    }
}

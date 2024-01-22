package com.e_commerce.Service;

import com.e_commerce.Entity.CartItem;
import com.e_commerce.Entity.Order;
import com.e_commerce.Entity.User;
import com.e_commerce.Repository.CartItemRepository;
import com.e_commerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService=orderService;
    }

    @Override
    public Order createOrder(Long userid) {
        User user = userService.getUserById(userid).orElse(null);
        if(user == null){
            throw new RuntimeException("User not found with ID:" + userid);
        }
        List<CartItem> cartItems = cartItemRepository.findByUserId(userid);

        if(cartItems.isEmpty()){
            throw new RuntimeException("User's cart is empty cannot create an order.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setCartItems(cartItems);
        // save order to databse
        orderRepository.save(order);

        // clear's the users cart after creating the order
        cartItemRepository.deleteAll(cartItems);
        return order;

    }

    public List<Order> getAllOrders(Order order){
        return orderRepository.findAll();
    }
}

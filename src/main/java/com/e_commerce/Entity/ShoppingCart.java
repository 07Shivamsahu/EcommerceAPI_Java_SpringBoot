package com.e_commerce.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setShoppingCart(null);
    }
}

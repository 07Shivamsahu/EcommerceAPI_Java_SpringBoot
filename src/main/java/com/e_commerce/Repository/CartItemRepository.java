package com.e_commerce.Repository;

import com.e_commerce.Entity.CartItem;
import com.e_commerce.Entity.Product;
import com.e_commerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userid);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}

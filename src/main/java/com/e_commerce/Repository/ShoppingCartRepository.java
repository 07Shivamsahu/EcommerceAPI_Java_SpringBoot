package com.e_commerce.Repository;

import com.e_commerce.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserId(Long userId);
}
